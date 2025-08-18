package com.example.filmeservice.infra.adapter;

import com.example.filmeservice.domain.ports.output.TmdbClientPort;
import com.example.filmeservice.infra.dto.MovieDto;
import com.example.filmeservice.infra.grpc.TmdbSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TmdbClientAdapter implements TmdbClientPort {

    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public TmdbClientAdapter(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.themoviedb.org/3")
                .build();
    }

    public List<MovieDto> searchMovies(String title) {
        TmdbSearchResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/movie")
                        .queryParam("query", title)
                        .build())
                .header("Authorization", "Bearer " + apiKey)
                .header("accept", "application/json")
                .retrieve()
                .bodyToMono(TmdbSearchResponse.class)
                .onErrorResume(e -> Mono.empty())
                .block();

        if (response == null || response.getResults() == null) return List.of();

        return response.getResults().stream()
                .map(movie -> {
                    String year = (movie.getReleaseDate() != null && movie.getReleaseDate().length() >= 4)
                            ? movie.getReleaseDate().substring(0, 4)
                            : "N/A";

                    return new MovieDto(
                            String.valueOf(movie.getId()),
                            movie.getTitle(),
                            year,
                            movie.getOverview(),
                            movie.getPosterPath() != null
                                    ? "https://image.tmdb.org/t/p/w500" + movie.getPosterPath()
                                    : null
                    );
                })
                .collect(Collectors.toList());
    }
}
