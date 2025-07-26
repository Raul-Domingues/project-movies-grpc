package com.example.filmeservice.domain.ports.input;

import com.example.filmeservice.domain.models.FilmeModel;

public interface BuscarFilmePort {
    FilmeModel buscarFilmepeloTitulo(String titulo);
}
