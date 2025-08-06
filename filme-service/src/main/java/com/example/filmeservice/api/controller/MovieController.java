package com.example.filmeservice.api.controller;

import com.example.filmeservice.grpc.MovieListResponse;
import com.example.filmeservice.grpc.MovieRequest;
import com.example.filmeservice.grpc.MovieServiceGrpc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {

    private final MovieServiceGrpc.MovieServiceBlockingStub grpcStub;

    public MovieController(MovieServiceGrpc.MovieServiceBlockingStub grpcStub) {
        this.grpcStub = grpcStub;
    }

    @GetMapping("/search-movie")
    public String searchMovie(@RequestParam("title") String title, Model model) {
        try {
            MovieRequest request = MovieRequest.newBuilder()
                    .setTitle(title)
                    .build();
            MovieListResponse response = grpcStub.searchMovie(request);
            model.addAttribute("movies", response.getMoviesList());
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao buscar filme: " + e.getMessage());
        }
        return "home";
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            MovieRequest request = MovieRequest.newBuilder()
                    .setTitle("popular")
                    .build();
            MovieListResponse response = grpcStub.searchMovie(request);
            model.addAttribute("movies", response.getMoviesList());
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar filmes: " + e.getMessage());
        }
        return "home";
    }

}
