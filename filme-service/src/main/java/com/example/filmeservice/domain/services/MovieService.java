package com.example.filmeservice.domain.services;

import com.example.filmeservice.domain.models.MovieModel;
import com.example.filmeservice.domain.ports.input.FindMoviePort;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import org.springframework.stereotype.Service;

@Service
public class MovieService implements FindMoviePort {

    private final OmdbClientPort omdbClientPort;

    public MovieService(OmdbClientPort omdbClientPort) {
        this.omdbClientPort = omdbClientPort;
    }

    @Override
    public MovieModel findMovieByTitle(String titulo) {
        return omdbClientPort.findMovie(titulo);
    }
}
