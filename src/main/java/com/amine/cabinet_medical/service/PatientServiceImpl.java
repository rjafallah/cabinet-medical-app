package com.amine.cabinet_medical.service;

import com.amine.cabinet_medical.dao.PatientDAO;
import com.amine.cabinet_medical.entite.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientDAO patientDAO;

    @Autowired
    public PatientServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public List<Patient> findAll() {
        return patientDAO.findAll();
    }

    @Override
    public Patient findById(int id) {
        Optional<Patient> resultat = patientDAO.findById(id);
        if (resultat.isPresent()) {
            return resultat.get();
        } else {
            return null;
        }
    }

    @Override
    public Patient findByUtilisateurUsername(String username) {
        return patientDAO.findByUtilisateurUsername(username);
    }

    @Override
    public void save(Patient patient) {
        patientDAO.save(patient);
    }

    @Override
    public void deleteById(int id) {
        patientDAO.deleteById(id);
    }
}