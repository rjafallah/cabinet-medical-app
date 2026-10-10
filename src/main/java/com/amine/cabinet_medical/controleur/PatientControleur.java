package com.amine.cabinet_medical.controleur;

import com.amine.cabinet_medical.entite.Patient;
import com.amine.cabinet_medical.entite.Medecin;
import com.amine.cabinet_medical.entite.RendezVous;
import com.amine.cabinet_medical.service.MedecinService;
import com.amine.cabinet_medical.service.PatientService;
import com.amine.cabinet_medical.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/patient")
public class PatientControleur {

    private PatientService patientService;
    private MedecinService medecinService;
    private RendezVousService rendezVousService;

    @Autowired
    public PatientControleur(PatientService patientService, MedecinService medecinService, RendezVousService rendezVousService) {
        this.patientService = patientService;
        this.medecinService = medecinService;
        this.rendezVousService = rendezVousService;
    }

    // tableau de bord
    @GetMapping("/tableau-de-bord")
    public String tableauDeBord(Model model, Principal principal) {
        Patient patient = patientService.findByUtilisateurUsername(principal.getName()); // patient connecté
        model.addAttribute("patient", patient);
        return "patient/tableau-de-bord";
    }

    // liste des médecins
    @GetMapping("/liste-medecins")
    public String listeMedecins(Model model) {
        model.addAttribute("medecins", medecinService.findAll());
        return "patient/liste-medecins";
    }

    // liste des rendez-vous
    @GetMapping("/rendez-vous")
    public String rendezVous(Model model, Principal principal) {
        Patient patient = patientService.findByUtilisateurUsername(principal.getName());
        model.addAttribute("rendezVous",rendezVousService.findByPatientId(patient.getId()));
        return "patient/rendez-vous";
    }

    // formulaire nouveau rendez-vous
    @GetMapping("/nouveau-rendez-vous")
    public String nouveauRendezVousForm(Model model) {
        model.addAttribute("rendezVous", new RendezVous());
        model.addAttribute("medecins", medecinService.findAll());
        return "patient/nouveau-rendez-vous";
    }

    // sauvegarder rendez-vous
    @PostMapping("/sauvegarder-rendez-vous")
    public String sauvegarderRendezVous(@ModelAttribute("rendezVous") RendezVous rendezVous, @RequestParam("medecin.id") int medecinId, Principal principal) {
        Patient patient = patientService.findByUtilisateurUsername(principal.getName());

        Medecin medecin = medecinService.findById(medecinId);

        rendezVous.setPatient(patient);
        rendezVous.setMedecin(medecin);
        rendezVous.setStatut("EN_ATTENTE");

        rendezVousService.save(rendezVous);
        return "redirect:/patient/rendez-vous";
    }

    // annuler rendez-vous
    @GetMapping("/annuler-rendez-vous/{id}")
    public String annulerRendezVous(@PathVariable int id) {
        rendezVousService.deleteById(id);
        return "redirect:/patient/rendez-vous";
    }
}