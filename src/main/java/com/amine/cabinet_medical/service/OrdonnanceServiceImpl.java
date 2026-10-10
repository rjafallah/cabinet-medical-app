package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.dao.OrdonnanceDAO;
import com.amine.cabinet_medical.entite.Ordonnance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdonnanceServiceImpl implements OrdonnanceService {

    private OrdonnanceDAO ordonnanceDAO;

    @Autowired
    public OrdonnanceServiceImpl(OrdonnanceDAO ordonnanceDAO) {
        this.ordonnanceDAO = ordonnanceDAO;
    }

    @Override
    public List<Ordonnance> findByMedecinId(int medecinId) {
        return ordonnanceDAO.findByMedecinId(medecinId);
    }

    @Override
    public List<Ordonnance> findByPatientId(int patientId) {
        return ordonnanceDAO.findByPatientId(patientId);
    }

    @Override
    public void save(Ordonnance ordonnance) {
        ordonnanceDAO.save(ordonnance);
    }

    @Override
    public void deleteById(int id) {
        ordonnanceDAO.deleteById(id);
    }
}