package com.restuser.restuser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_Voyage")
public class Voyage {

    @Id
    @Column(name = "PK_Voyage", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "Destination", length = 50)
    private String destination;

    @Column(name = "FK_Utilisateur", length = 50)
    private Integer fk_utilisateur;
    @Column(name = "FK_Fusee", length = 50)
    private Integer fk_fusee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_Herisson")
    private Herisson herisson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getUtilisateur() {
        return fk_utilisateur;
    }

    public void setUtilisateur(Integer fk_utilisateur) {
        this.fk_utilisateur = fk_utilisateur;
    }

    public Integer getFusee() {
        return fk_fusee;
    }

    public void setFusee(Integer fk_fusee) {
        this.fk_fusee = fk_fusee;
    }

    public Herisson getHerisson() {
        return herisson;
    }

    public void setHerisson(Herisson herisson) {
        this.herisson = herisson;
    }

}
