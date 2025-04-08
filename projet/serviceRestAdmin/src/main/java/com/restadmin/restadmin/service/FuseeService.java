package com.restadmin.restadmin.service;

import org.springframework.stereotype.Service;

import com.restadmin.restadmin.model.Fusee;
import com.restadmin.restadmin.repository.FuseeRepository;

@Service
public class FuseeService {
    private final FuseeRepository fuseeRepository;

    public FuseeService(FuseeRepository fuseeRepository) {
        this.fuseeRepository = fuseeRepository;
    }

    public String addFusee(String nom) {
        Iterable<Fusee> fusees = getAllFusees();
        for (Fusee fusee : fusees) {
            if (fusee.getNom() == nom) {
                return "name already used";
            }
        }
        Fusee newFusee = new Fusee();
        newFusee.setNom(nom);
        fuseeRepository.save(newFusee);
        return "saved " + nom;
    }

    public String demonteFusee(Integer id) {
        String name = fuseeRepository.findById(id).get().getNom();
        fuseeRepository.deleteById(id);
        return "discarded " + name;
    }

    public String modifieFusee(Integer id, String name) {
        String oldName = "";
        Fusee fusee = fuseeRepository.findById(id).get();
        oldName = fusee.getNom();
        fusee.setNom(name);
        fuseeRepository.save(fusee);
        return "modified " + oldName + " to " + name;
    }

    public Iterable<Fusee> getAllFusees() {
        Iterable<Fusee> fuseeTab = fuseeRepository.findAll();

        return fuseeTab;
    }

}