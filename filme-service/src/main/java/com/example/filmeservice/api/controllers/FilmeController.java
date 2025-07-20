package com.example.filmeservice.api.controllers;

import com.example.filmeservice.domain.models.FilmeModel;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final OmdbClientPort omdbClientPort;

    public FilmeController(OmdbClientPort omdbClientPort) {
        this.omdbClientPort = omdbClientPort;
    }

    @GetMapping("/{titulo}")
    public FilmeModel buscarFilme(@PathVariable String titulo) {
        return omdbClientPort.buscarFilme(titulo);
    }
}
