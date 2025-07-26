package com.example.filmeservice.domain.services;

import com.example.filmeservice.domain.models.FilmeModel;
import com.example.filmeservice.domain.ports.input.BuscarFilmePort;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import org.springframework.stereotype.Service;

@Service
public class BuscarFilmeService implements BuscarFilmePort {

    private final OmdbClientPort omdbClientPort;

    public BuscarFilmeService(OmdbClientPort omdbClientPort) {
        this.omdbClientPort = omdbClientPort;
    }

    @Override
    public FilmeModel buscarFilmepeloTitulo(String titulo) {
        return omdbClientPort.buscarFilme(titulo);
    }
}
