package com.example.filmeservice.infra.adapter;

import com.example.filmeservice.domain.models.FilmeModel;
import com.example.filmeservice.domain.ports.output.FavoritoRepositoryPort;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import com.example.filmeservice.infra.entities.FilmeFavoritoEntity;
import com.example.filmeservice.infra.repositories.JpaRepositoryFavoritos;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FavoritoRepositoryAdapter implements FavoritoRepositoryPort {

    private final JpaRepositoryFavoritos repo;
    private final OmdbClientPort omdbClient;

    public FavoritoRepositoryAdapter(JpaRepositoryFavoritos repo,
                                     OmdbClientPort omdbClient) {
        this.repo = repo;
        this.omdbClient = omdbClient;
    }

    @Override
    public void salvar(String imdbID) {
        FilmeModel filme = omdbClient.buscarFilmePorId(imdbID); // buscar detalhes
        FilmeFavoritoEntity entity = new FilmeFavoritoEntity();
        entity.setImdbID(filme.imdbID());
        entity.setTitulo(filme.Title());
        entity.setDataInclusao(LocalDateTime.now());
        repo.save(entity);
    }

}
