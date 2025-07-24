package com.example.filmeservice.infra.repositories;

import com.example.filmeservice.infra.entities.FilmeFavoritoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRepositoryFavoritos extends JpaRepository<FilmeFavoritoEntity, Long> {
}
