package com.amine.cabinet_medical.entite;

import jakarta.persistence.*;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "role")
    private String role;

    // constructeurs
    public Utilisateur() {}

    public Utilisateur(String username, String password, boolean enabled, String role) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.role = role;
    }

    // getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "Utilisateur{id=" + id + ", username='" + username + "', role='" + role + "'}";
    }
}