//package com.example.filmeservice.config;
//
//import com.example.filmeservice.infra.grpc.MovieServiceGrpcImpl;
//import io.grpc.Server;
//import jakarta.annotation.PreDestroy;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class GrpcServerConfig {
//
//    private final GrpcClientPropertiesConfig grpcClientPropertiesConfig;
//    private final MovieServiceGrpcImpl movieServiceGrpcImpl;
//
//    private Server server;
//
//    @EventListener(ContextRefreshedEvent.class)
//    public void startGrpcServer() {
//        try {
//            int port = grpcClientPropertiesConfig.getPort();
//            server = io.grpc.ServerBuilder.forPort(port)
//                    .addService(movieServiceGrpcImpl)
//                    .build()
//                    .start();
//            log.info("gRPC Server started on port: {}", port);
//        } catch (Exception e) {
//            log.error("Failed to start gRPC Server", e);
//        }
//    }
//
//    @PreDestroy
//    public void stopGrpcServer() {
//        if (server != null) {
//            server.shutdown();
//            log.info("gRPC Server stopped");
//        }
//    }
//}
