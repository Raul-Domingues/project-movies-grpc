package com.example.filmeservice.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MovieDto {
    private String id;
    private String title;
    private String year;
    private String overview;
    private String posterUrl;
}
