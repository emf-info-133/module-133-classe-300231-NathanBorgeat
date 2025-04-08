package com.restadmin.restadmin.repository;

import org.springframework.data.repository.CrudRepository;

import com.restadmin.restadmin.model.Fusee;

// This will be AUTO IMPLEMENTED by Spring into a Bean called SkieurRepository
// CRUD refers Create, Read, Update, Delete

public interface FuseeRepository extends CrudRepository<Fusee, Integer> {

}
