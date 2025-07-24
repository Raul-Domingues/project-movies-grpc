package com.example.filmeservice.infra.grpc;

import com.example.filmeservice.domain.models.FilmeModel;
import com.example.filmeservice.domain.ports.input.BuscarFilmePort;
import com.example.filmeservice.domain.ports.output.FavoritoRepositoryPort;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import com.example.filmeservice.grpc.FilmeServiceGrpc;
import com.example.filmeservice.grpc.FilmeServiceProto;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class FilmeServiceGrpcImpl extends FilmeServiceGrpc.FilmeServiceImplBase {

    private final OmdbClientPort omdbClient;
    private final FavoritoRepositoryPort favoritoRepository;

    public FilmeServiceGrpcImpl(OmdbClientPort omdbClient, FavoritoRepositoryPort favoritoRepository) {
        this.omdbClient = omdbClient;
        this.favoritoRepository = favoritoRepository;
    }

    @Override
    public void buscarFilme(FilmeServiceProto.FilmeRequest request, StreamObserver<FilmeServiceProto.FilmeResponse> responseObserver) {
        FilmeModel filme = omdbClient.buscarFilme(request.getTitle());
        responseObserver.onNext(toProto(filme));
        responseObserver.onCompleted();
    }

    @Override
    public void marcarFavorito(FilmeServiceProto.FavoritoRequest request, StreamObserver<Empty> responseObserver) {
        favoritoRepository.salvar(request.getImdbID());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    private FilmeServiceProto.FilmeResponse toProto(FilmeModel model) {
        return FilmeServiceProto.FilmeResponse.newBuilder()
                .setTitle(model.Title() != null ? model.Title() : "")
                .setYear(model.Year() != null ? model.Year() : "")
                .setGenre(model.Genre() != null ? model.Genre() : "")
                .setDirector(model.Director() != null ? model.Director() : "")
                .setPlot(model.Plot() != null ? model.Plot() : "")
                .setPoster(model.Poster() != null ? model.Poster() : "")
                .setImdbRating(model.imdbRating() != null ? model.imdbRating() : "")
                .setImdbID(model.imdbID() != null ? model.imdbID() : "")
                .setType(model.Type() != null ? model.Type() : "")
                .build();
    }
}
