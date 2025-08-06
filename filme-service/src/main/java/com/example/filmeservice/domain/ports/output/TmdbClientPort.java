package com.example.filmeservice.domain.ports.output;

import com.example.filmeservice.domain.models.MovieModel;

import java.util.List;

public interface TmdbClientPort {
    List<MovieModel> searchMovies(String title);
}
