package com.example.filmeservice.infra.client;

import com.example.filmeservice.domain.models.FilmeModel;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OmdbServiceAdapter implements OmdbClientPort {

    private final WebClient webClient;

    public OmdbServiceAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Value("${omdb.api.key}")
    private String apiKey;

    @Override
    public FilmeModel buscarFilme(String titulo) {
        FilmeModel response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("t", titulo)
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(FilmeModel.class)
                .block();

        System.out.println("Response from OMDB: " + response);
        return response;
    }
}
