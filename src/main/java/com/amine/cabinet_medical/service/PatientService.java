package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.entite.Patient;
import java.util.List;

public interface PatientService {

    List<Patient> findAll();
    Patient findById(int id);
    Patient findByUtilisateurUsername(String username);
    void save(Patient patient);
    void deleteById(int id);
}