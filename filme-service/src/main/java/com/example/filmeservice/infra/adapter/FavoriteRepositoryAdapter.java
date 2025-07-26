package com.example.filmeservice.infra.adapter;

import com.example.filmeservice.domain.models.MovieModel;
import com.example.filmeservice.domain.ports.output.FavoriteRepositoryPort;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import com.example.filmeservice.infra.entities.FavoriteMovieEntity;
import com.example.filmeservice.infra.repositories.JpaFavoritesRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FavoriteRepositoryAdapter implements FavoriteRepositoryPort {

    private final JpaFavoritesRepository repository;
    private final OmdbClientPort omdbClient;

    public FavoriteRepositoryAdapter(JpaFavoritesRepository repository,
                                     OmdbClientPort omdbClient) {
        this.repository = repository;
        this.omdbClient = omdbClient;
    }

    @Override
    public void save(String imdbID) {
        MovieModel movie = omdbClient.fetchMovieById(imdbID);
        FavoriteMovieEntity entity = new FavoriteMovieEntity();
        entity.setImdbID(movie.imdbID());
        entity.setTitle(movie.Title());
        entity.setAddedDate(LocalDateTime.now());
        repository.save(entity);
    }
}
