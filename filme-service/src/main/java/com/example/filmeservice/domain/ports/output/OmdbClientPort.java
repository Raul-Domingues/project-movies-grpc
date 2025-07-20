package com.example.filmeservice.domain.ports.output;

import com.example.filmeservice.domain.models.FilmeModel;
import org.springframework.stereotype.Component;

@Component
public interface OmdbClientPort {
    FilmeModel buscarFilme(String titulo);
    FilmeModel buscarFilmePorId(String id);
}
