package com.example.filmeservice.infra.grpc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TmdbSearchResponse {
    private List<TmdbMovieResult> results;

    public List<TmdbMovieResult> getResults() {
        return results;
    }

    public void setResults(List<TmdbMovieResult> results) {
        this.results = results;
    }

    @Data
    public static class TmdbMovieResult {
        private int id;
        private String title;

        @JsonProperty("release_date")
        private String releaseDate;

        private String overview;

        @JsonProperty("poster_path")
        private String posterPath;
    }
}
