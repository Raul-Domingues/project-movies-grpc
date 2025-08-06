package com.example.filmeservice.infra.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieDto {
    private String id;
    private String title;
    private String year;
    private String overview;
    private String posterUrl;
}
