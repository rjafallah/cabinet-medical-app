-- =============================================
-- Cabinet Médical - Script de création
-- =============================================

DROP DATABASE cabinet_medical;

CREATE DATABASE IF NOT EXISTS cabinet_medical;
USE cabinet_medical;

-- ---------------------------------------------
-- Table utilisateurs
-- ---------------------------------------------
CREATE TABLE utilisateurs (
    id          INT NOT NULL AUTO_INCREMENT,
    username    VARCHAR(50) NOT NULL UNIQUE,
    password    CHAR(255) NOT NULL,
    enabled     TINYINT NOT NULL DEFAULT 1,
    role        VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

-- ---------------------------------------------
-- Table médecins
-- ---------------------------------------------
CREATE TABLE medecins (
    id              INT NOT NULL AUTO_INCREMENT,
    nom             VARCHAR(50) NOT NULL,
    prenom          VARCHAR(50) NOT NULL,
    specialite      VARCHAR(100) NOT NULL,
    telephone       VARCHAR(20),
    email           VARCHAR(100),
    utilisateur_id  INT NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id)
);

-- ---------------------------------------------
-- Table patients
-- ---------------------------------------------
CREATE TABLE patients (
    id              INT NOT NULL AUTO_INCREMENT,
    nom             VARCHAR(50) NOT NULL,
    prenom          VARCHAR(50) NOT NULL,
    date_naissance  DATE NOT NULL,
    telephone       VARCHAR(20),
    email           VARCHAR(100),
    utilisateur_id  INT NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateurs(id)
);

-- ---------------------------------------------
-- Table rendez-vous
-- ---------------------------------------------
CREATE TABLE rendez_vous (
    id          INT NOT NULL AUTO_INCREMENT,
    date_heure  DATETIME NOT NULL,
    motif       VARCHAR(255),
    statut      VARCHAR(20) NOT NULL DEFAULT 'EN_ATTENTE',
    medecin_id  INT NOT NULL,
    patient_id  INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (medecin_id) REFERENCES medecins(id),
    FOREIGN KEY (patient_id) REFERENCES patients(id)
);

-- ---------------------------------------------
-- Table ordonnances
-- ---------------------------------------------
CREATE TABLE ordonnances (
    id          INT NOT NULL AUTO_INCREMENT,
    date        DATE NOT NULL,
    contenu     TEXT NOT NULL,
    medecin_id  INT NOT NULL,
    patient_id  INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (medecin_id) REFERENCES medecins(id),
    FOREIGN KEY (patient_id) REFERENCES patients(id)
);

-- ---------------------------------------------
-- Données de test
-- ---------------------------------------------
INSERT INTO utilisateurs VALUES
(1, 'admin', 'admin123', 1, 'ADMIN'),
(2, 'docteur1', 'dr123', 1, 'MEDECIN'),
(3, 'docteur2', 'dr123', 1, 'MEDECIN'),
(4, 'patient1', 'test123', 1, 'PATIENT'),
(5, 'patient2', 'test123', 1, 'PATIENT');

INSERT INTO medecins VALUES
(1, 'Rja-fallah', 'Yassine',   'Généraliste',  '0600000001', 'Rjafallah@cabinet.fr', 2),
(2, 'Toto', 'Soufiane', 'Cardiologue',  '0600000002', 'toto@cabinet.fr', 3);

INSERT INTO patients VALUES
(1, 'Rja-fallah', 'Amine',  '1990-05-15', '0600000003', 'amine@email.fr',  4),
(2, 'Larhissi', 'Marie', '1985-09-22', '0600000004', 'marie@email.fr', 5);

INSERT INTO rendez_vous VALUES
(1, '2026-03-10 09:00:00', 'Consultation générale', 'EN_ATTENTE', 1, 1),
(2, '2026-03-11 14:00:00', 'Suivi cardiologique',   'CONFIRME',   2, 2);

INSERT INTO ordonnances VALUES
(1, '2026-03-01', 'Paracétamol 1g - 3x par jour pendant 5 jours', 1, 1),
(2, '2026-03-02', 'Aspirine 100mg - 1x par jour', 2, 2);
