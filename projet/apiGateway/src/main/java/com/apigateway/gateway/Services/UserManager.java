package com.apigateway.gateway.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class UserManager {

    private final RestTemplate restTemplate;

    private static final String url = System.getenv().getOrDefault("RESTUSER_URL", "http://localhost:8081/restUser/");

    @Autowired
    public UserManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> addVoyage(String destination, Integer herissonId, Integer fk_utilisateur,
            Integer fk_fusee) {

        Map<String, String> voyageInfos = new HashMap<>();
        voyageInfos.put("destination", destination);
        voyageInfos.put("herissonId", herissonId.toString());
        voyageInfos.put("fk_utilisateur", fk_utilisateur.toString());
        voyageInfos.put("fk_fusee", fk_fusee.toString());

        ResponseEntity<String> response = restTemplate.postForEntity(url + "addVoyage", voyageInfos, String.class);

        return response;
    }

    public ResponseEntity<String> getVoyages(Integer fk_utilisateur) {

        MultiValueMap<String, String> voyageInfos = new LinkedMultiValueMap<>();
        voyageInfos.add("fk_utilisateur", fk_utilisateur.toString());

        String urlFinal = UriComponentsBuilder.fromUriString(url + "getVoyages")
                .queryParams(voyageInfos)
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(urlFinal, String.class);

        return response;
    }

    public ResponseEntity<String> getAllVoyages() {

        ResponseEntity<String> response = restTemplate.getForEntity(url + "getAllVoyages", String.class);

        return response;
    }

    public ResponseEntity<String> addHerisson(String nom, String caracteristique, Integer fk_utilisateur) {

        Map<String, String> herissonInfos = new HashMap<>();
        herissonInfos.put("nom", nom);
        herissonInfos.put("caracteristique", caracteristique);
        herissonInfos.put("fk_utilisateur", fk_utilisateur.toString());

        ResponseEntity<String> response = restTemplate.postForEntity(url + "addHerisson", herissonInfos, String.class);

        return response;
    }

    public ResponseEntity<String> modifyHerisson(Integer pk_herisson, String nom, String caracteristique, Integer fk_utilisateur) {

        Map<String, String> herissonInfos = new HashMap<>();
        herissonInfos.put("pk_herisson", pk_herisson.toString());
        herissonInfos.put("nom", nom);
        herissonInfos.put("caracteristique", caracteristique);
        herissonInfos.put("fk_utilisateur", fk_utilisateur.toString());

        ResponseEntity<String> response = restTemplate.postForEntity(url + "putHerisson", herissonInfos, String.class);

        return response;
    }

    public ResponseEntity<String> getHerissons(Integer fk_utilisateur) {

        MultiValueMap<String, String> herissonInfos = new LinkedMultiValueMap<>();
        herissonInfos.add("fk_utilisateur", fk_utilisateur.toString());

        String urlFinal = UriComponentsBuilder.fromUriString(url + "getHerissons")
                .queryParams(herissonInfos)
                .toUriString();

        ResponseEntity<String> response = restTemplate.getForEntity(urlFinal, String.class);

        return response;
    }
}
