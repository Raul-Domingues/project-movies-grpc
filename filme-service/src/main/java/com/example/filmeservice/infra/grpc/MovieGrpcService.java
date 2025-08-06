package com.example.filmeservice.infra.grpc;

import com.example.filmeservice.grpc.Movie;
import com.example.filmeservice.grpc.MovieListResponse;
import com.example.filmeservice.grpc.MovieRequest;
import com.example.filmeservice.grpc.MovieServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class MovieGrpcService extends MovieServiceGrpc.MovieServiceImplBase {

    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey;

    public MovieGrpcService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.themoviedb.org/3")
                .build();
    }

    @Override
    public void searchMovie(MovieRequest request, StreamObserver<MovieListResponse> responseObserver) {
        String query = request.getTitle();

        try {
            String path = query.isEmpty() ? "/movie/popular" : "/search/movie";
            TmdbSearchResponse tmdbResponse = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(path)
                            .queryParam("query", query)
                            .build())
                    .header("Authorization", "Bearer " + apiKey)
                    .header("accept", "application/json")
                    .retrieve()
                    .bodyToMono(TmdbSearchResponse.class)
                    .block();

            List<Movie> movieList = (tmdbResponse != null && tmdbResponse.getResults() != null)
                    ? tmdbResponse.getResults().stream().map(tmdbMovie -> {
                String year = Optional.ofNullable(tmdbMovie.getReleaseDate())
                        .filter(date -> date.length() >= 4)
                        .map(date -> date.substring(0, 4))
                        .orElse("N/A");

                return Movie.newBuilder()
                        .setId(String.valueOf(tmdbMovie.getId()))
                        .setTitle(tmdbMovie.getTitle())
                        .setYear(year)
                        .setOverview(Optional.ofNullable(tmdbMovie.getOverview()).orElse(""))
                        .setPosterUrl(
                                tmdbMovie.getPosterPath() != null
                                        ? "https://image.tmdb.org/t/p/w500" + tmdbMovie.getPosterPath()
                                        : ""
                        )
                        .build();
            }).toList()
                    : List.of();

            MovieListResponse response = MovieListResponse.newBuilder()
                    .addAllMovies(movieList)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Erro ao buscar filmes: " + e.getMessage())
                    .asRuntimeException());
        }
    }
}
