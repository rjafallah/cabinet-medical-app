package com.amine.cabinet_medical.entite;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ordonnances")
public class Ordonnance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "contenu")
    private String contenu;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    // constructeurs
    public Ordonnance() {}

    public Ordonnance(LocalDate date, String contenu) {
        this.date = date;
        this.contenu = contenu;
    }

    // getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public Medecin getMedecin() { return medecin; }
    public void setMedecin(Medecin medecin) { this.medecin = medecin; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    @Override
    public String toString() {
        return "Ordonnance{id=" + id + ", date=" + date + "}";
    }
}