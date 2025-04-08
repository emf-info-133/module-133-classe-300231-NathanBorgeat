package com.restuser.restuser.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_herisson")
public class Herisson {

    @Id
    @Column(name = "pk_herisson")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nom", length = 50)
    private String name;
    @Column(name = "caracterisitique", length = 50)
    private String caracteristique;
    @Column(name = "fk_utilisateur")
    private Integer fkUtilisateur;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaracteristique() {
        return caracteristique;
    }

    public void setCaracteristique(String caracteristique) {
        this.caracteristique = caracteristique;
    }

    public Integer getUtilisateur() {
        return fkUtilisateur;
    }

    public void setUtilisateur(Integer fk_utilisateur) {
        this.fkUtilisateur = fk_utilisateur;
    }

}
