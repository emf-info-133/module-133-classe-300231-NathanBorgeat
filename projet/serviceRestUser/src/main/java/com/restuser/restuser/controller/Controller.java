package com.restuser.restuser.controller;

import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;

@RestController
public class Controller {

    // Handler pour GET
    @GetMapping(path = "/visites")
    public ResponseEntity<String> getVisites(HttpSession httpsession) {
        if (httpsession.getAttribute("username") != null) {
            Integer visites = (Integer) httpsession.getAttribute("visites");
            visites++;
            
            httpsession.setAttribute("visites", visites);

            return ResponseEntity.ok("Visites : " + visites);
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }

    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(HttpSession httpsession, @RequestParam String username,
            @RequestParam String password) {
        if ("user".equals(username) && "pass".equals(password)) {
            httpsession.setAttribute("username", username);
            httpsession.setAttribute("visites", 0);
            return ResponseEntity.ok("Connecté");
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<String> logout(HttpSession httpsession) {
        httpsession.invalidate();
        return ResponseEntity.ok("Déconnecté");
    }

}