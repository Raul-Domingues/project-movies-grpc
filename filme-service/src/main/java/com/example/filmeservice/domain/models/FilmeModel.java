package com.example.filmeservice.domain.models;

public record FilmeModel(
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
