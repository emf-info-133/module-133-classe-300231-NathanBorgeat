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
@Table(name = "t_voyage")
public class Voyage {

    @Id
    @Column(name = "pk_voyage")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "destination", length = 50)
    private String destination;

    @Column(name = "fk_utilisateur")
    private Integer fkUtilisateur;
    @Column(name = "fk_fusee")
    private Integer fk_fusee;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_herisson")
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
        return fkUtilisateur;
    }

    public void setUtilisateur(Integer fk_utilisateur) {
        this.fkUtilisateur = fk_utilisateur;
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
