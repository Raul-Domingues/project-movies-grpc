package com.example.filmeservice.infra.repositories;

import com.example.filmeservice.infra.entities.FavoriteMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFavoritesRepository extends JpaRepository<FavoriteMovieEntity, Long> {
}
