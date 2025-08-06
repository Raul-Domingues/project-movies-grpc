package com.example.filmeservice.domain.models;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieModel {
    private String id;
    private String title;
    private String year;
    private String overview;
    private String posterUrl;
}