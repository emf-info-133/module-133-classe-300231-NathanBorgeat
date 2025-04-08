package com.restuser.restuser.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restuser.restuser.model.Herisson;
import com.restuser.restuser.model.Voyage;
import com.restuser.restuser.repository.HerissonRepository;
import com.restuser.restuser.repository.VoyageRepository;

import jakarta.transaction.Transactional;

@Service
public class VoyageService {
    private final VoyageRepository voyageRepository;
    private final HerissonRepository herissonRepository;

    @Autowired
    public VoyageService(VoyageRepository voyageRepository, HerissonRepository herissonRepository) {
        this.voyageRepository = voyageRepository;
        this.herissonRepository = herissonRepository;
    }

    public String addVoyage(String destination, Integer herissonId, Integer fk_utilisateur, Integer fk_fusee) {
        Herisson herisson = herissonRepository.findById(herissonId).orElse(null);
        if (herisson == null) {
            return "Herisson not found";
        }

        if (herisson.getUtilisateur() != fk_utilisateur) {
            return "Herisson not available";
        }
        Voyage newVoyage = new Voyage();
        newVoyage.setDestination(destination);
        newVoyage.setHerisson(herisson);
        newVoyage.setUtilisateur(fk_utilisateur);
        newVoyage.setFusee(fk_fusee);
        voyageRepository.save(newVoyage);
        return "saved";
    }

    // getVoyages pour un utilisateur
    public List<Voyage> getVoyages(Integer fk_utilisateur) {
        Iterable<Voyage> voyagesIterable = voyageRepository.findByFkUtilisateur(fk_utilisateur);
        List<Voyage> voyages = (List<Voyage>) voyagesIterable;
        return voyages;
    }

    // getAllVoyages pour un admin
    public List<Voyage> getAllVoyages() {
        Iterable<Voyage> voyagesIterable = voyageRepository.findAll();
        List<Voyage> voyages = new ArrayList<>();
        for (Voyage voyageIterable : voyagesIterable) {
            Voyage voyage = new Voyage();
            voyage.setId(voyageIterable.getId());
            voyage.setDestination(voyageIterable.getDestination());
            voyage.setHerisson(voyageIterable.getHerisson());
            voyage.setUtilisateur(voyageIterable.getUtilisateur());
            voyage.setFusee(voyageIterable.getFusee());
            voyages.add(voyage);
        }
        return voyages;
    }
}