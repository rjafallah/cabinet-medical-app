package com.amine.cabinet_medical.dao;

import com.amine.cabinet_medical.entite.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinDAO extends JpaRepository<Medecin, Integer> {

    Medecin findByUtilisateurUsername(String username);
}
