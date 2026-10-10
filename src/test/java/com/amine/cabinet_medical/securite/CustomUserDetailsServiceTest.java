package com.amine.cabinet_medical.securite;

import com.amine.cabinet_medical.dao.UtilisateurDAO;
import com.amine.cabinet_medical.entite.Utilisateur;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UtilisateurDAO utilisateurDAO;

    @InjectMocks
    private CustomUserDetailsService service;

    @Test
    void chargeUnUtilisateurExistantAvecSonRole() {
        when(utilisateurDAO.findByUsername("docteur1"))
                .thenReturn(new Utilisateur("docteur1", "hash", true, "MEDECIN"));

        UserDetails details = service.loadUserByUsername("docteur1");

        assertEquals("docteur1", details.getUsername());
        assertEquals("hash", details.getPassword());
        assertTrue(details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MEDECIN")));
    }

    @Test
    void leveUneExceptionSiUtilisateurInconnu() {
        when(utilisateurDAO.findByUsername("inconnu")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> service.loadUserByUsername("inconnu"));
    }
}
