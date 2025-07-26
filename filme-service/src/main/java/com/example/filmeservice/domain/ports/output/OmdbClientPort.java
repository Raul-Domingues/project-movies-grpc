package com.example.filmeservice.domain.ports.output;

import com.example.filmeservice.domain.models.MovieModel;
import org.springframework.stereotype.Component;

@Component
public interface OmdbClientPort {
    MovieModel findMovie(String titulo);
    MovieModel findMovieById(String id);
}
