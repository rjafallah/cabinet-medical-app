package com.amine.cabinet_medical.controleur;

import com.amine.cabinet_medical.entite.Medecin;
import com.amine.cabinet_medical.entite.Ordonnance;
import com.amine.cabinet_medical.entite.RendezVous;
import com.amine.cabinet_medical.service.MedecinService;
import com.amine.cabinet_medical.service.OrdonnanceService;
import com.amine.cabinet_medical.service.PatientService;
import com.amine.cabinet_medical.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/medecin")
public class MedecinControleur {

    private MedecinService medecinService;
    private PatientService patientService;
    private RendezVousService rendezVousService;
    private OrdonnanceService ordonnanceService;

    @Autowired
    public MedecinControleur(MedecinService medecinService, PatientService patientService, RendezVousService rendezVousService, OrdonnanceService ordonnanceService) {
        this.medecinService = medecinService;
        this.patientService = patientService;
        this.rendezVousService = rendezVousService;
        this.ordonnanceService = ordonnanceService;
    }

    // tableau de bord
    @GetMapping("/tableau-de-bord")
    public String tableauDeBord(Model model, Principal principal) {
        Medecin medecin = medecinService.findByUtilisateurUsername(principal.getName());
        model.addAttribute("medecin", medecin);
        return "medecin/tableau-de-bord";
    }

    // planning rendez-vous
    @GetMapping("/planning")
    public String planning(Model model, Principal principal) {
        Medecin medecin = medecinService.findByUtilisateurUsername(principal.getName());
        model.addAttribute("rendezVous", rendezVousService.findByMedecinId(medecin.getId()));
        return "medecin/planning";
    }

    // liste patients
    @GetMapping("/patients")
    public String patients(Model model) {
        model.addAttribute("patients", patientService.findAll());
        return "medecin/patients";
    }

    // formulaire ordonnance
    @GetMapping("/ajouter-ordonnance/{patientId}")
    public String ajouterOrdonnanceForm(@PathVariable int patientId, Model model) {
        model.addAttribute("ordonnance", new Ordonnance());
        model.addAttribute("patient", patientService.findById(patientId));
        return "medecin/ajouter-ordonnance";
    }

    // sauvegarder ordonnance
    @PostMapping("/sauvegarder-ordonnance/{patientId}")
    public String sauvegarderOrdonnance(@ModelAttribute("ordonnance") Ordonnance ordonnance, @PathVariable int patientId, Principal principal) {
        Medecin medecin = medecinService.findByUtilisateurUsername(principal.getName());
        ordonnance.setMedecin(medecin);
        ordonnance.setPatient(patientService.findById(patientId));
        ordonnance.setDate(LocalDate.now());
        ordonnanceService.save(ordonnance);
        return "redirect:/medecin/patients";
    }

    // confirmer rendez-vous
    @GetMapping("/confirmer-rendez-vous/{id}")
    public String confirmerRendezVous(@PathVariable int id) {
        RendezVous rendezVous = rendezVousService.findById(id);
        rendezVous.setStatut("CONFIRME");
        rendezVousService.save(rendezVous);
        return "redirect:/medecin/planning";
    }
}