package com.amine.cabinet_medical.controleur;

import com.amine.cabinet_medical.service.MedecinService;
import com.amine.cabinet_medical.service.PatientService;
import com.amine.cabinet_medical.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminControleur {

    private MedecinService medecinService;
    private PatientService patientService;
    private RendezVousService rendezVousService;

    @Autowired
    public AdminControleur(MedecinService medecinService, PatientService patientService, RendezVousService rendezVousService) {
        this.medecinService = medecinService;
        this.patientService = patientService;
        this.rendezVousService = rendezVousService;
    }

    // tableau de bord
    @GetMapping("/tableau-de-bord")
    public String tableauDeBord(Model model) {
        model.addAttribute("nombreMedecins", medecinService.findAll().size());
        model.addAttribute("nombrePatients", patientService.findAll().size());
        model.addAttribute("nombreRendezVous", rendezVousService.findAll().size());
        return "admin/tableau-de-bord";
    }

    // liste médecins
    @GetMapping("/medecins")
    public String medecins(Model model) {
        model.addAttribute("medecins", medecinService.findAll());
        return "admin/medecins";
    }

    // supprimer médecin
    @GetMapping("/supprimer-medecin/{id}")
    public String supprimerMedecin(@PathVariable int id) {
        medecinService.deleteById(id);
        return "redirect:/admin/medecins";
    }

    // liste patients
    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientService.findAll());
        return "admin/patients";
    }

    // supprimer patient
    @GetMapping("/supprimer-patient/{id}")
    public String supprimerPatient(@PathVariable int id) {
        patientService.deleteById(id);
        return "redirect:/admin/patients";
    }
}