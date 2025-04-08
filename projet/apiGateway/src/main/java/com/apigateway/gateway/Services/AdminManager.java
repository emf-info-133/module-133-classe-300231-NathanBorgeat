package com.apigateway.gateway.Services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpSession;

public class AdminManager {

    private final RestTemplate restTemplate;

    private static final String url = System.getenv().getOrDefault("RESTADMIN_URL", "http://localhost:8082/restAdmin/");

    @Autowired
    public AdminManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> signin(HttpSession httpsession, @RequestParam String username,
            @RequestParam String password) {

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", username);
        credentials.put("password", password);

        ResponseEntity<String> response = restTemplate.postForEntity(url + "signin", credentials, String.class);
            
        return response;
    }

    public ResponseEntity<String> login(HttpSession httpsession, @RequestParam String username,
            @RequestParam String password) {

        Map<String, String> credentials = new HashMap<>();
        credentials.put("name", username);
        credentials.put("password", password);

        ResponseEntity<Map> response = restTemplate.postForEntity(url + "login", credentials, Map.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            
            Map responseBody = response.getBody();
            if (responseBody != null) {
                // Configurer la session en cas de succès
                httpsession.setAttribute("pk", responseBody.get("pk_user"));
                httpsession.setAttribute("admin", responseBody.get("admin"));
                return ResponseEntity.ok("Connecté");
            }
        }

        return ResponseEntity.badRequest().body("Non connecté");
    }

}
