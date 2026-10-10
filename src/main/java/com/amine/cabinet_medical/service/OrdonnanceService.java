package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.entite.Ordonnance;
import java.util.List;

public interface OrdonnanceService {

    List<Ordonnance> findByMedecinId(int medecinId);
    List<Ordonnance> findByPatientId(int patientId);
    void save(Ordonnance ordonnance);
    void deleteById(int id);
}