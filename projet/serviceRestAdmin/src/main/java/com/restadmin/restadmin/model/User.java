package com.restadmin.restadmin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_User")
    private Integer id;

    @Column(name = "Nom", length = 45)
    private String nom;

    @Column(name = "Mot_de_passe", length = 255)
    private String mdp;

    @Column(name = "Admin")
    private boolean isAdmin;

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public boolean getAdmin() {
        return isAdmin;
    }


}
