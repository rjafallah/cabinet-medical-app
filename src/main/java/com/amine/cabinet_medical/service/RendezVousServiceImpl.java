package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.dao.RendezVousDAO;
import com.amine.cabinet_medical.entite.RendezVous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RendezVousServiceImpl implements RendezVousService {

    private RendezVousDAO rendezVousDAO;

    @Autowired
    public RendezVousServiceImpl(RendezVousDAO rendezVousDAO) {
        this.rendezVousDAO = rendezVousDAO;
    }

    @Override
    public List<RendezVous> findAll() {
        return rendezVousDAO.findAll();
    }

    @Override
    public RendezVous findById(int id) {
        Optional<RendezVous> resultat = rendezVousDAO.findById(id);
        if (resultat.isPresent()) {
            return resultat.get();
        } else {
            return null;
        }
    }

    @Override
    public List<RendezVous> findByMedecinId(int medecinId) {
        return rendezVousDAO.findByMedecinId(medecinId);
    }

    @Override
    public List<RendezVous> findByPatientId(int patientId) {
        return rendezVousDAO.findByPatientId(patientId);
    }

    @Override
    public void save(RendezVous rendezVous) {
        rendezVousDAO.save(rendezVous);
    }

    @Override
    public void deleteById(int id) {
        rendezVousDAO.deleteById(id);
    }
}