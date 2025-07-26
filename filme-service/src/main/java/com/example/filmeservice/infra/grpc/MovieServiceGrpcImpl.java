package com.example.filmeservice.infra.grpc;

import com.example.filmeservice.domain.models.MovieModel;
import com.example.filmeservice.domain.ports.output.FavoriteRepositoryPort;
import com.example.filmeservice.domain.ports.output.OmdbClientPort;
import com.example.filmeservice.grpc.MovieServiceGrpc;
import com.example.filmeservice.grpc.MovieServiceProto;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class MovieServiceGrpcImpl extends MovieServiceGrpc.MovieServiceImplBase {

    private final OmdbClientPort omdbClient;
    private final FavoriteRepositoryPort favoriteRepository;

    public MovieServiceGrpcImpl(OmdbClientPort omdbClient, FavoriteRepositoryPort favoriteRepository) {
        this.omdbClient = omdbClient;
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public void searchMovie(MovieServiceProto.MovieRequest request, StreamObserver<MovieServiceProto.MovieResponse> responseObserver) {
        MovieModel movie = omdbClient.fetchMovie(request.getTitle());
        responseObserver.onNext(toProto(movie));
        responseObserver.onCompleted();
    }

    @Override
    public void markFavorite(MovieServiceProto.FavoriteRequest request, StreamObserver<Empty> responseObserver) {
        favoriteRepository.save(request.getImdbID());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    private MovieServiceProto.MovieResponse toProto(MovieModel model) {
        return MovieServiceProto.MovieResponse.newBuilder()
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
