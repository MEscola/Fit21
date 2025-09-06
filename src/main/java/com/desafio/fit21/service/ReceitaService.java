package com.desafio.fit21.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReceitaService {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://api.spoonacular.com/recipes";

    private final RestTemplate restTemplate = new RestTemplate();

    public String getReceitaAleatoria() {
        String url = BASE_URL + "/random?apiKey=" + apiKey + "&number=1";
        
        return restTemplate.getForObject(url, String.class);
    }

    public String getReceitaPorCategoria(String tipo) {
        String url = BASE_URL + "/complexSearch?apiKey=" + apiKey + "&number=3&type=" + tipo;
        return restTemplate.getForObject(url, String.class);
    }
}

