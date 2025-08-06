package com.example.filmeservice.domain.services;

import com.example.filmeservice.domain.ports.input.FindMoviePort;
import com.example.filmeservice.domain.ports.output.TmdbClientPort;
import com.example.filmeservice.infra.dto.MovieDto;

import java.util.List;

public class FindMovieService implements FindMoviePort {

    private final TmdbClientPort tmdbClientPort;

    public FindMovieService(TmdbClientPort tmdbClientPort) {
        this.tmdbClientPort = tmdbClientPort;
    }

    @Override
    public List<MovieDto> searchMovies(String title) {
        return tmdbClientPort.searchMovies(title);
    }
}
