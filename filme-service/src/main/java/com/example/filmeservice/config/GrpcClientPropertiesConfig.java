package com.example.filmeservice.config;

import com.example.filmeservice.grpc.MovieServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "grpc.server")
public class GrpcClientPropertiesConfig {
    private int port = 9090;
    private int maxInboundMessageSize;

    @Bean
    public ManagedChannel movieServiceChannel() {
        return ManagedChannelBuilder
                .forAddress("localhost", 9090) // ou porta correta
                .usePlaintext() // apenas para testes
                .build();
    }

    @Bean
    public MovieServiceGrpc.MovieServiceBlockingStub movieServiceBlockingStub(ManagedChannel movieServiceChannel) {
        return MovieServiceGrpc.newBlockingStub(movieServiceChannel);
    }
}
