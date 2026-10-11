# Cabinet Médical — application et CI

Application web de gestion de cabinet médical (Spring Boot), livrée sur Kubernetes par une chaîne **GitOps** complète : CI GitHub Actions, déploiement ArgoCD, infrastructure Terraform sur AWS, observabilité Prometheus / Grafana / Loki.

Ce dépôt contient le code de l'application et le pipeline CI. Le projet complet repose sur trois dépôts :

| Dépôt | Contenu |
|---|---|
| **cabinet-medical-app** (ici) | code Spring Boot, tests, Dockerfile, docker-compose, workflow CI |
| [cabinet-medical-gitops](https://github.com/rjafallah/cabinet-medical-gitops) | chart Helm, valeurs d'environnement, manifests ArgoCD |
| [cabinet-medical-infra](https://github.com/rjafallah/cabinet-medical-infra) | Terraform (EC2) et script de bootstrap du cluster |

## Architecture

```
git push ──► GitHub Actions : mvn test ─► docker build ─► push Docker Hub (tag = SHA)
                                                  │
                                                  ▼ commit du nouveau tag
                                       cabinet-medical-gitops (source de vérité)
                                                  │
                                                  ▼ ArgoCD tire (pull) et synchronise
   EC2 (Terraform) ── cluster kubeadm 1 nœud ─────────────────────────────┐
        ├─ argocd      : ArgoCD                                           │
        ├─ dev         : application Spring Boot + MySQL (chart Helm)     │
        └─ monitoring  : Prometheus, Grafana, Loki, Promtail  ◄───────────┘
```

Principes :

- La CI **ne touche jamais le cluster** : elle pousse une image et modifie Git. C'est ArgoCD qui applique.
- Le tag d'image est le **SHA du commit** (immuable, traçable), jamais `latest`.
- **Rollback = `git revert`** du commit de tag, ArgoCD resynchronise.
- `selfHeal` corrige toute modification manuelle du cluster, `prune` supprime ce qui n'est plus dans Git.

## Pipeline CI

Workflow [`.github/workflows/ci.yml`](.github/workflows/ci.yml), déclenché à chaque push sur `main` :

| Job | Rôle |
|---|---|
| `test` | `mvn test` (H2 en mémoire, aucune base externe) |
| `build-push` | construit l'image et la pousse sur Docker Hub, tag = SHA court |
| `update-image-tag` | met à jour `image.tag` dans le dépôt gitops et pousse le commit |

Secrets requis dans le dépôt : `DOCKERHUB_USERNAME`, `DOCKERHUB_TOKEN`, `GITOPS_PAT` (token limité au dépôt gitops).

## Lancer l'application en local

Prérequis : Docker.

```bash
docker compose up --build
```

- Application : <http://localhost:8080/connexion>
- Santé : <http://localhost:8080/actuator/health>
- Métriques : <http://localhost:8080/actuator/prometheus>

Comptes de test (jeu de données `base_donnees/base_donnees.sql`, mots de passe hachés en BCrypt) :

| Utilisateur | Mot de passe | Rôle |
|---|---|---|
| `admin` | `admin123` | administrateur |
| `docteur1` | `dr123` | médecin |
| `patient1` | `test123` | patient |

Le script SQL ne s'exécute qu'à la création du volume : après l'avoir modifié, lancer `docker compose down -v`.

## Tests

```bash
mvn test
```

Profil `test` avec H2 en mode MySQL : chargement du contexte, encodage BCrypt, `UserDetailsService`, service des rendez-vous.

## Démo en 5 minutes

1. **Déploiement automatique** : modifier un fichier, `git push`. Observer les trois jobs dans l'onglet *Actions*, puis le commit `ci: bump image tag to <sha>` dans le dépôt gitops, puis la synchronisation dans ArgoCD.
2. **Observabilité** : dans Grafana, le dashboard *JVM (Micrometer)* affiche la nouvelle instance ; dans *Explore → Loki*, `{namespace="dev"}` montre les logs de démarrage.
3. **Dérive** : `kubectl scale deploy cabinet-medical -n dev --replicas=3` ; ArgoCD remet le nombre de réplicas à 1.
4. **Rollback** : `git revert` du commit de bump dans le dépôt gitops, puis `git push` ; l'image précédente revient.

## Choix techniques et limites

| Sujet | Choix / limite |
|---|---|
| Cluster | un seul nœud kubeadm (control-plane et worker confondus) : pas de haute disponibilité |
| Environnements | un seul (`dev`) ; pas d'environnement de production distinct |
| Secrets | identifiants de test en clair dans le chart ; Sealed Secrets non mis en place |
| Réseau | pas d'Ingress ni de TLS ; interfaces accessibles par tunnel SSH ; pas de NetworkPolicy (Flannel) |
| Alerting | Alertmanager désactivé, pas de règles d'alerte ; pas de métriques métier applicatives |
| Logs | `loki-stack` (chart déprécié) ; l'histogramme de volume de logs de Grafana est en erreur avec Loki 2.9 |
| Données | pas de sauvegarde MySQL ; volume `local-path` perdu à la destruction du cluster |
| Image | tests rejoués pendant `docker build` en plus de la CI |
