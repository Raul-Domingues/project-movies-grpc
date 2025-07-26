package com.example.filmeservice.domain.models;

public record MovieModel(
        String Title,
        String Year,
        String Genre,
        String Director,
        String Plot,
        String Poster,
        String imdbRating,
        String imdbID,
        String Type
) {}
