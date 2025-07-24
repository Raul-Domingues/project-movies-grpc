package com.example.filmeservice.infra.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class FilmeFavoritoEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String imdbID;
    private String titulo;
    private LocalDateTime dataInclusao;
}
