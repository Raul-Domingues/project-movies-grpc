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

    private final JpaFavoritesRepository jpaFavoritesRepository;
    private final OmdbClientPort omdbClientPort;

    public FavoriteRepositoryAdapter(JpaFavoritesRepository jpaFavoritesRepository,
                                     OmdbClientPort omdbClientPort) {
        this.jpaFavoritesRepository = jpaFavoritesRepository;
        this.omdbClientPort = omdbClientPort;
    }

    @Override
    public void save(String imdbID) {
        MovieModel movie = omdbClientPort.findMovieById(imdbID);
        FavoriteMovieEntity entity = new FavoriteMovieEntity();
        entity.setImdbID(movie.imdbID());
        entity.setTitle(movie.Title());
        entity.setAddedDate(LocalDateTime.now());
        jpaFavoritesRepository.save(entity);
    }
}
