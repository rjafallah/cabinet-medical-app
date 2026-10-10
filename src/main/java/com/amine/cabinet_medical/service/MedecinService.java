package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.entite.Medecin;
import java.util.List;

public interface MedecinService {

    List<Medecin> findAll();
    Medecin findById(int id);
    Medecin findByUtilisateurUsername(String username);
    void save(Medecin medecin);
    void deleteById(int id);
}