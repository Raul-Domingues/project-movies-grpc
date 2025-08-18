package com.example.filmeservice.api.controller;

import com.example.filmeservice.domain.ports.input.FindMoviePort;
import com.example.filmeservice.infra.dto.MovieDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    private final FindMoviePort findMoviePort;

    public MovieController(FindMoviePort findMoviePort) {
        this.findMoviePort = findMoviePort;
    }

    @GetMapping("/search-movie")
    public String searchMovie(@RequestParam("title") String title, Model model) {
        try {
            List<MovieDto> movies = findMoviePort.searchMovies(title);
            model.addAttribute("movies", movies);
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao buscar filme: " + e.getMessage());
        }
        return "home";
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            List<MovieDto> movies = findMoviePort.searchMovies("popular");
            model.addAttribute("movies", movies);
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar filmes: " + e.getMessage());
        }
        return "home";
    }
}
