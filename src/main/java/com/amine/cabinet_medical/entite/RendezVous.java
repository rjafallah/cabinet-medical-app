package com.amine.cabinet_medical.entite;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rendez_vous")
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date_heure")
    private LocalDateTime dateHeure;

    @Column(name = "motif")
    private String motif;

    @Column(name = "statut")
    private String statut;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // constructeurs
    public RendezVous() {}

    public RendezVous(LocalDateTime dateHeure, String motif, String statut) {
        this.dateHeure = dateHeure;
        this.motif = motif;
        this.statut = statut;
    }

    // getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    @Override
    public String toString() {
        return "RendezVous{id=" + id + ", dateHeure=" + dateHeure + ", statut='" + statut + "'}";
    }
}