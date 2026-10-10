package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.dao.UtilisateurDAO;
import com.amine.cabinet_medical.entite.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurDAO utilisateurDAO;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public Utilisateur findByUsername(String username) {
        return utilisateurDAO.findByUsername(username);
    }

    @Override
    public void save(Utilisateur utilisateur) {
        utilisateurDAO.save(utilisateur);
    }
}