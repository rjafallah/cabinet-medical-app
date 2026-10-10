package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.entite.Utilisateur;

public interface UtilisateurService {

    Utilisateur findByUsername(String username);
    void save(Utilisateur utilisateur);
}