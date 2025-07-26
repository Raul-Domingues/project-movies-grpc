package com.example.filmeservice.infra.repositories;

import com.example.filmeservice.infra.entities.FavoriteMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFavoritesRepository extends JpaRepository<FavoriteMovieEntity, Long> {
}
