package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.entite.RendezVous;
import java.util.List;

public interface RendezVousService {

    List<RendezVous> findAll();
    RendezVous findById(int id);
    List<RendezVous> findByMedecinId(int medecinId);
    List<RendezVous> findByPatientId(int patientId);
    void save(RendezVous rendezVous);
    void deleteById(int id);
}