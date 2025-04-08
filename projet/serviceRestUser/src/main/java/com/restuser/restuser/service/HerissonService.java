package com.restuser.restuser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restuser.restuser.model.Herisson;
import com.restuser.restuser.repository.HerissonRepository;

@Service
public class HerissonService {

    private final HerissonRepository herissonRepository;

    @Autowired
    public HerissonService(HerissonRepository herissonRepository) {
        this.herissonRepository = herissonRepository;
    }

    public String addHerisson(String nom, String caracteristique, Integer fk_utilisateur) {
        Herisson newHerisson = new Herisson();
        newHerisson.setName(nom);
        newHerisson.setCaracteristique(caracteristique);
        newHerisson.setUtilisateur(fk_utilisateur);
        herissonRepository.save(newHerisson);
        return "saved";
    }

    public String modifyHerisson(Integer pk_herisson, String nom, String caracteristique, Integer fk_utilisateur) {
        try {
            Herisson herisson = herissonRepository.findById(pk_herisson).get();
            if (herisson.getUtilisateur() != fk_utilisateur) {
                return "Herisson not available";
            }
            herisson.setName(nom);
            herisson.setCaracteristique(caracteristique);
            herissonRepository.save(herisson);
            return "modified";
        } catch (NoSuchElementException e) {
            return "herisson not found";
        }
    }

    public List<Herisson> getHerissons(Integer fk_utilisateur) {
        Iterable<Herisson> herissonsIterable = herissonRepository.findByFkUtilisateur(fk_utilisateur);
        List<Herisson> herissons = (List<Herisson>) herissonsIterable;
        return herissons;
    }
}