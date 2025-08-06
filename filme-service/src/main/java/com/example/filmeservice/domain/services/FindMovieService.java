package com.example.filmeservice.domain.services;

import com.example.filmeservice.domain.models.MovieModel;
import com.example.filmeservice.domain.ports.input.FindMoviePort;
import com.example.filmeservice.domain.ports.output.TmdbClientPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindMovieService implements FindMoviePort {
    private final TmdbClientPort tmdbClientPort;

    public FindMovieService(TmdbClientPort tmdbClientPort) {
        this.tmdbClientPort = tmdbClientPort;
    }

    @Override
    public List<MovieModel> findMovieByTitle(String title) {
        return tmdbClientPort.searchMovies(title);
    }
}
