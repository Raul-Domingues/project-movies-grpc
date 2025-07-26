package com.example.filmeservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.filmeservice.infra.entities")
@EnableJpaRepositories(basePackages = "com.example.filmeservice.infra.repositories")
public class FilmeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmeServiceApplication.class, args);
    }
}