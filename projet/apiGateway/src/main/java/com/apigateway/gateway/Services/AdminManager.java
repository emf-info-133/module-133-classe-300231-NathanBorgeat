package com.apigateway.gateway.Services;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

public class AdminManager {

    private final RestTemplate restTemplate;

    private final String url;

    public AdminManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        url = System.getenv().getOrDefault("RESTADMIN_URL", "http://localhost:8082/restAdmin/");
    }

    public ResponseEntity<String> signin(HttpSession httpsession, String username,
            @RequestParam String password) {

        MultiValueMap<String, String> credentials = new LinkedMultiValueMap<>();
        credentials.add("username", username);
        credentials.add("password", password);

        ResponseEntity<String> response = restTemplate.postForEntity(url + "signin", credentials, String.class);

        return response;
    }

    public ResponseEntity<String> login(HttpSession httpsession, String username,
            @RequestParam String password) {

        MultiValueMap<String, String> credentials = new LinkedMultiValueMap<>();
        credentials.add("username", username);
        credentials.add("password", password);

        ResponseEntity<Map> response = restTemplate.postForEntity(url + "login", credentials, Map.class);
        if (response.getStatusCode().is2xxSuccessful()) {

            Map responseBody = response.getBody();
            if (responseBody != null) {
                // Configurer la session en cas de succès
                httpsession.setAttribute("pk", responseBody.get("id"));
                httpsession.setAttribute("admin", responseBody.get("admin"));
                return ResponseEntity.ok("Connecté");
            }
        }

        return ResponseEntity.badRequest().body("Non connecté");
    }

    public ResponseEntity<String> addFusee(String nom) {
        MultiValueMap<String, String> fuseeInfos = new LinkedMultiValueMap<>();
        fuseeInfos.add("name", nom);

        ResponseEntity<String> response = restTemplate.postForEntity(url + "addFusee", fuseeInfos, String.class);

        return response;
    }

    public ResponseEntity<String> modifieFusee(Integer pk_fusee, String nom) {

        MultiValueMap<String, String> fuseeInfos = new LinkedMultiValueMap<>();
        fuseeInfos.add("id", pk_fusee.toString());
        fuseeInfos.add("name", nom);

        ResponseEntity<String> response = restTemplate.exchange(url + "modifieFusee", HttpMethod.PUT,
                new HttpEntity<>(fuseeInfos, new HttpHeaders()), String.class);

        return response;
    }

    public ResponseEntity<String> demonteFusee(Integer pk_fusee) {

        MultiValueMap<String, String> fuseeInfos = new LinkedMultiValueMap<>();
        fuseeInfos.add("id", pk_fusee.toString());

        ResponseEntity<String> response = restTemplate.exchange(url + "demonteFusee", HttpMethod.DELETE,
                new HttpEntity<>(fuseeInfos, new HttpHeaders()), String.class);
        return response;
    }

    public ResponseEntity<String> getFusee() {

        ResponseEntity<String> response = restTemplate.getForEntity(url + "getFusee", String.class);

        return response;
    }

    public ResponseEntity<String> getUsers() {

        ResponseEntity<String> response = restTemplate.getForEntity(url + "getUsers", String.class);

        return response;
    }

}
