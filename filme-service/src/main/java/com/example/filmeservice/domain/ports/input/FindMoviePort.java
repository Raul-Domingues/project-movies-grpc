package com.example.filmeservice.domain.ports.input;

import com.example.filmeservice.infra.dto.MovieDto;

import java.util.List;

public interface FindMoviePort {
    List<MovieDto> searchMovies(String title);
}
