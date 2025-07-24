package com.example.filmeservice.infra.client;

import com.example.filmeservice.domain.models.FilmeModel;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OmdbServiceAdapter implements OmdbClientPort {

    private final WebClient webClient;
    private final String apiKey;

    public OmdbServiceAdapter(WebClient webClient, @Value("${omdb.api.key}") String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    @Override
    public FilmeModel buscarFilme(String titulo) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("t", titulo).queryParam("apikey", apiKey).build())
                .retrieve()
                .bodyToMono(FilmeModel.class)
                .block();
    }

    @Override
    public FilmeModel buscarFilmePorId(String id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("i", id).queryParam("apikey", apiKey).build())
                .retrieve()
                .bodyToMono(FilmeModel.class)
                .block();
    }
}
