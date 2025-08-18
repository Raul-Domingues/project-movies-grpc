package com.example.filmeservice.domain.services;

import com.example.filmeservice.domain.ports.input.FindMoviePort;
import com.example.filmeservice.domain.ports.output.TmdbClientPort;
import com.example.filmeservice.infra.dto.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements FindMoviePort {

    private final TmdbClientPort tmdbClientPort;

    public MovieService(TmdbClientPort tmdbClientPort) {
        this.tmdbClientPort = tmdbClientPort;
    }

    @Override
    public List<MovieDto> searchMovies(String title) {
        return tmdbClientPort.searchMovies(title);
    }
}
