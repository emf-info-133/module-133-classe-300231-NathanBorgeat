package com.restuser.restuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restuser.restuser.model.Herisson;
import com.restuser.restuser.model.Voyage;
import com.restuser.restuser.service.HerissonService;
import com.restuser.restuser.service.VoyageService;

@RestController
@RequestMapping("/restUser")
public class Controller {

    private final HerissonService herissonService;
    private final VoyageService voyageService;

    @Autowired
    public Controller(HerissonService herissonService, VoyageService voyageService) {
        this.herissonService = herissonService;
        this.voyageService = voyageService;
    }

    // Handler pour GET
    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    @GetMapping(path = "/getVoyages")
    public @ResponseBody List<Voyage> getVoyages(@RequestParam Integer fk_utilisateur) {
        return voyageService.getVoyages(fk_utilisateur);
    }

    @GetMapping(path = "/getAllVoyages")
    public @ResponseBody List<Voyage> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    @GetMapping(path = "/getHerissons")
    public @ResponseBody List<Herisson> getHerissons(@RequestParam Integer fk_utilisateur) {
        return herissonService.getHerissons(fk_utilisateur);
    }

    @PostMapping(path = "/addVoyage")
    public @ResponseBody String addNewVoyage(@RequestParam String destination, @RequestParam Integer herissonId,
            @RequestParam Integer fk_utilisateur, @RequestParam Integer fk_fusee) {
        return voyageService.addVoyage(destination, herissonId, fk_utilisateur, fk_fusee);
    }

    @PostMapping(path = "/addHerisson")
    public @ResponseBody String addNewHerisson(@RequestParam String nom, @RequestParam String caracteristique,
            @RequestParam Integer fk_utilisateur) {
        return herissonService.addHerisson(nom, caracteristique, fk_utilisateur);
    }

    @PutMapping("/putHerisson")
    public @ResponseBody String putHerisson(@RequestParam Integer pk_herisson, @RequestParam String nom,
            @RequestParam String caracteristique, @RequestParam Integer fk_utilisateur) {
        return herissonService.modifyHerisson(pk_herisson, nom, caracteristique, fk_utilisateur);
    }

}