package com.amine.cabinet_medical.dao;

import com.amine.cabinet_medical.entite.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RendezVousDAO extends JpaRepository<RendezVous, Integer> {

    List<RendezVous> findByMedecinId(int medecinId);
    List<RendezVous> findByPatientId(int patientId);
}