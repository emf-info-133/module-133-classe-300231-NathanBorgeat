package com.restuser.restuser.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.restuser.restuser.model.Voyage;

// This will be AUTO IMPLEMENTED by Spring into a Bean called SkieurRepository
// CRUD refers Create, Read, Update, Delete

public interface VoyageRepository extends CrudRepository<Voyage, Integer> {
    public List<Voyage> findByFkUtilisateur(Integer fk_utilisateur);

}
