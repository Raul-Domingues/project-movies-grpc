package com.example.filmeservice.api.controller;

import com.example.filmeservice.grpc.MovieServiceGrpc;
import com.example.filmeservice.grpc.MovieServiceProto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieViewController {

    private final MovieServiceGrpc.MovieServiceBlockingStub grpcStub;

    public MovieViewController(MovieServiceGrpc.MovieServiceBlockingStub grpcStub) {
        this.grpcStub = grpcStub;
    }

    @PostMapping("/search-movie")
    public String searchMovie(@RequestParam String title, Model model) {
        MovieServiceProto.MovieRequest request = MovieServiceProto.MovieRequest.newBuilder()
                .setTitle(title)
                .build();
        MovieServiceProto.MovieResponse response = grpcStub.searchMovie(request);
        model.addAttribute("movie", response);
        return "home"; // certifique-se de que o nome da página é "home.html"
    }


    @PostMapping("/favorite")
    public String favorite(@RequestParam String imdbID) {
        grpcStub.markFavorite(MovieServiceProto.FavoriteRequest.newBuilder()
                .setImdbID(imdbID)
                .build());
        return "redirect:/";
    }
}
