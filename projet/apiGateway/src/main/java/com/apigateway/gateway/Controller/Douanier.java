package com.apigateway.gateway.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.apigateway.gateway.Services.AdminManager;
import com.apigateway.gateway.Services.UserManager;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/apiGateway")
public class Douanier {

    private final AdminManager adminManager;
    private final UserManager userManager;

    @Autowired
    public Douanier(AdminManager adminManager, UserManager userManager) {
        this.adminManager = adminManager;
        this.userManager = userManager;
    }

    // Handler pour GET
    @GetMapping("/")
    public String getNothing() {
        return "";
    }

    // Partie User
    // A ameliorer (par exemple ne pas demander la fk_utilisateur mais prendre la
    // variable de session)

    @GetMapping(path = "/getVoyages")
    public @ResponseBody ResponseEntity<String> getVoyages(HttpSession httpsession) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            return userManager.getVoyages((Integer) httpsession.getAttribute("pk"));
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @GetMapping(path = "/getAllVoyages")
    public @ResponseBody ResponseEntity<String> getAllVoyages(HttpSession httpsession) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            if ((boolean) httpsession.getAttribute("admin") == false) {
                return ResponseEntity.badRequest().body("Compte admin requis");
            }
            return userManager.getAllVoyages();
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @GetMapping(path = "/getHerissons")
    public @ResponseBody ResponseEntity<String> getHerissons(HttpSession httpsession) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            return userManager.getHerissons((Integer) httpsession.getAttribute("pk"));
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @PostMapping(path = "/addVoyage")
    public @ResponseBody ResponseEntity<String> addNewVoyage(HttpSession httpsession, @RequestParam String destination,
            @RequestParam Integer herissonId, @RequestParam Integer fk_fusee) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            return userManager.addVoyage(destination, herissonId, (Integer) httpsession.getAttribute("pk"), fk_fusee);
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @PostMapping(path = "/addHerisson")
    public @ResponseBody ResponseEntity<String> addNewHerisson(HttpSession httpsession, @RequestParam String nom,
            @RequestParam String caracteristique) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            return userManager.addHerisson(nom, caracteristique, (Integer) httpsession.getAttribute("pk"));
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @PutMapping("/putHerisson")
    public ResponseEntity<String> putHerisson(HttpSession httpsession, @RequestParam Integer pk_herisson,
            @RequestParam String nom,
            @RequestParam String caracteristique) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            return userManager.modifyHerisson(pk_herisson, nom, caracteristique,
                    (Integer) httpsession.getAttribute("pk"));
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    // Partie Admin

    @PostMapping(path = "/signin")
    public ResponseEntity<String> signin(HttpSession httpsession, @RequestParam String username,
            @RequestParam String password) {

        return adminManager.signin(httpsession, username, password);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(HttpSession httpsession, @RequestParam String username,
            @RequestParam String password) {

        return adminManager.login(httpsession, username, password);

    }

    @PostMapping(path = "/logout")
    public ResponseEntity<String> logout(HttpSession httpsession) {
        httpsession.invalidate();
        return ResponseEntity.ok("Déconnecté");
    }

    @PostMapping(path = "/addFusee")
    public @ResponseBody ResponseEntity<String> addNewFusee(HttpSession httpsession, @RequestParam String nom) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            if ((boolean) httpsession.getAttribute("admin") == false) {
                return ResponseEntity.badRequest().body("Compte admin requis");
            }
            return adminManager.addFusee(nom);
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @PutMapping("/modifieFusee")
    public ResponseEntity<String> modifieFusee(HttpSession httpsession, @RequestParam Integer pk_fusee,
            @RequestParam String nom) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            if ((boolean) httpsession.getAttribute("admin") == false) {
                return ResponseEntity.badRequest().body("Compte admin requis");
            }
            return adminManager.modifieFusee(pk_fusee, nom);
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @DeleteMapping("/demonteFusee")
    public ResponseEntity<String> demonteFusee(HttpSession httpsession, @RequestParam Integer pk_fusee) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            if ((boolean) httpsession.getAttribute("admin") == false) {
                return ResponseEntity.badRequest().body("Compte admin requis");
            }
            return adminManager.demonteFusee(pk_fusee);
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }

    @GetMapping(path = "/getFusee")
    public @ResponseBody ResponseEntity<String> getFusee(HttpSession httpsession) {
        if (httpsession.getAttribute("pk") != null && httpsession.getAttribute("admin") != null) {
            return adminManager.getFusee();
        } else {
            return ResponseEntity.badRequest().body("Non connecté");
        }
    }
}