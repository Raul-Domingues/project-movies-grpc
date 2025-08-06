package com.example.filmeservice.domain.ports.output;

import com.example.filmeservice.infra.dto.MovieDto;

import java.util.List;

public interface TmdbClientPort {
    List<MovieDto> searchMovies(String title);
}
