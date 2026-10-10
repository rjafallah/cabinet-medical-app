package com.amine.cabinet_medical.dao;

import com.amine.cabinet_medical.entite.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdonnanceDAO extends JpaRepository<Ordonnance, Integer> {

    List<Ordonnance> findByMedecinId(int medecinId);
    List<Ordonnance> findByPatientId(int patientId);
}