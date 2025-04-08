package com.restadmin.restadmin.dto;

public class UserDTO {
    private Integer id;
    private String nom;
    private boolean admin;

    // Constructeurs, getters et setters
    public UserDTO() {}

    public UserDTO(Integer id, String nom, boolean admin) {
        this.id = id;
        this.nom = nom;
        this.admin = admin;
    }

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

    public boolean getAdmin(){
        return admin;
    }
     public void setAdmin(boolean admin){
        this.admin = admin;
     }

}
