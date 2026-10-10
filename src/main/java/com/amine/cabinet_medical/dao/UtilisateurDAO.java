package com.amine.cabinet_medical.dao;

import com.amine.cabinet_medical.entite.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurDAO extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByUsername(String username);
}