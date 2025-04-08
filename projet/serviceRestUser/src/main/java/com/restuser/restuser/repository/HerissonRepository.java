package com.restuser.restuser.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.restuser.restuser.model.Herisson;

// This will be AUTO IMPLEMENTED by Spring into a Bean called SkieurRepository
// CRUD refers Create, Read, Update, Delete

public interface HerissonRepository extends CrudRepository<Herisson, Integer> {
    public List<Herisson> findByFkUtilisateur (Integer fk_utilisateur);

}