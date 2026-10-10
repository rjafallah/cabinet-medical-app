package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.dao.MedecinDAO;
import com.amine.cabinet_medical.entite.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class MedecinServiceImpl implements MedecinService {

    private MedecinDAO medecinDAO;

    @Autowired
    public MedecinServiceImpl(MedecinDAO medecinDAO) {
        this.medecinDAO = medecinDAO;
    }

    @Override
    public List<Medecin> findAll() {
        return medecinDAO.findAll();
    }

    @Override
    public Medecin findById(int id) {
        Optional<Medecin> resultat = medecinDAO.findById(id);
        if (resultat.isPresent()) {
            return resultat.get();
        } else {
            return null;
        }
    }

    @Override
    public Medecin findByUtilisateurUsername(String username) {
        return medecinDAO.findByUtilisateurUsername(username);
    }

    @Override
    public void save(Medecin medecin) {
        medecinDAO.save(medecin);
    }

    @Override
    public void deleteById(int id) {
        medecinDAO.deleteById(id);
    }
}