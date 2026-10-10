package com.amine.cabinet_medical.entite;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medecins")
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "specialite")
    private String specialite;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RendezVous> rendezVous;

    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ordonnance> ordonnances;

    // constructeurs
    public Medecin() {}

    public Medecin(String nom, String prenom, String specialite, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
        this.telephone = telephone;
        this.email = email;
    }

    // getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }

    public List<RendezVous> getRendezVous() { return rendezVous; }
    public void setRendezVous(List<RendezVous> rendezVous) { this.rendezVous = rendezVous; }

    public List<Ordonnance> getOrdonnances() { return ordonnances; }
    public void setOrdonnances(List<Ordonnance> ordonnances) { this.ordonnances = ordonnances; }

    @Override
    public String toString() {
        return "Medecin{id=" + id + ", nom='" + nom + "', prenom='" + prenom + "'}";
    }
}