package com.amine.cabinet_medical.dao;

import com.amine.cabinet_medical.entite.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDAO extends JpaRepository<Patient, Integer> {

    Patient findByUtilisateurUsername(String username);
}