package com.amine.cabinet_medical.securite;

import com.amine.cabinet_medical.dao.UtilisateurDAO;
import com.amine.cabinet_medical.entite.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Arrays;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurDAO.findByUsername(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
        }

        return new User(
            utilisateur.getUsername(),
            utilisateur.getPassword(),
            Arrays.asList(
                new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole())
            )
        );
    }
}