package com.example.filmeservice.domain.ports.input;

import com.example.filmeservice.domain.models.MovieModel;

import java.util.List;

public interface FindMoviePort {
    List<MovieModel> findMovieByTitle(String title);
}
