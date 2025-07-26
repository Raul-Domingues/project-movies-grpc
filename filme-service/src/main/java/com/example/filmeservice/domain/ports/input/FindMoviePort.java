package com.example.filmeservice.domain.ports.input;

import com.example.filmeservice.domain.models.MovieModel;

public interface FindMoviePort {
    MovieModel findMovieByTitle(String title);
}
