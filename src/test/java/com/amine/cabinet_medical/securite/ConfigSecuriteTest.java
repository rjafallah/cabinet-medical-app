package com.amine.cabinet_medical.securite;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class ConfigSecuriteTest {

    private final PasswordEncoder encoder = new ConfigSecurite().passwordEncoder();

    @Test
    void utiliseBCrypt() {
        assertInstanceOf(BCryptPasswordEncoder.class, encoder);
    }

    @Test
    void lHashNEstPasLeMotDePasseEnClair() {
        String hash = encoder.encode("admin123");

        assertNotEquals("admin123", hash);
        assertTrue(encoder.matches("admin123", hash));
        assertFalse(encoder.matches("mauvais", hash));
    }

    @Test
    void leHashDuSeedSqlCorrespondAuMotDePasse() {
        // hash de l'utilisateur "admin" dans base_donnees.sql
        String hashSeed = "$2a$10$ZgLOwe8gG2bjOL3PRY.XkuMvHkLlnRAr5GGaImFJen2koBqM7XpHK";

        assertTrue(encoder.matches("admin123", hashSeed));
    }
}
