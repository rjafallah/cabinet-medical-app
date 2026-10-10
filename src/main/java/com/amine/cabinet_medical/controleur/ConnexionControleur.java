package com.amine.cabinet_medical.controleur;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Controller
public class ConnexionControleur {

    @GetMapping("/connexion")
    public String afficherConnexion() {
        return "connexion";
    }

    @GetMapping("/acces-refuse")
    public String afficherAccesRefuse() {
        return "acces-refuse";
    }

    @GetMapping("/tableau-de-bord")
    public String redirigerTableauDeBord(Authentication authentication) {
        if (authentication.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/tableau-de-bord";
        } else if (authentication.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_MEDECIN"))) {
            return "redirect:/medecin/tableau-de-bord";
        } else {
            return "redirect:/patient/tableau-de-bord";
        }
    }
}