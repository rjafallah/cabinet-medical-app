package com.amine.cabinet_medical.securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class ConfigSecurite {

    // BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    // AuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    // SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
            configurer
                .requestMatchers("/connexion").permitAll()
                .requestMatchers("/acces-refuse").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/medecin/**").hasRole("MEDECIN")
                .requestMatchers("/patient/**").hasRole("PATIENT")
                .anyRequest().authenticated()
        )
        .formLogin(form ->
            form
                .loginPage("/connexion")
                .loginProcessingUrl("/authentifier")
                .defaultSuccessUrl("/tableau-de-bord", true)
                .permitAll()
        )
        .logout(logout ->
            logout
                .logoutUrl("/deconnexion")
                .logoutSuccessUrl("/connexion?deconnexion")
                .permitAll()
        )
        .exceptionHandling(configurer ->
            configurer
                .accessDeniedPage("/acces-refuse")
        );

        return http.build();
    }
}