package com.example.filmeservice.api.controller;

import com.example.filmeservice.grpc.FilmeServiceGrpc;
import com.example.filmeservice.grpc.FilmeServiceProto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmeViewController {

    private final FilmeServiceGrpc.FilmeServiceBlockingStub grpcStub;

    public FilmeViewController(FilmeServiceGrpc.FilmeServiceBlockingStub grpcStub,
                               Model model) {
        this.grpcStub = grpcStub;
        model.addAttribute("filme", new FilmeServiceProto.FilmeResponse());
    }

    @PostMapping("/buscar-filme")
    public String buscarFilme(@RequestParam String titulo, Model model) {
        FilmeServiceProto.FilmeRequest request = FilmeServiceProto.FilmeRequest.newBuilder().setTitle(titulo).build();
        FilmeServiceProto.FilmeResponse response = grpcStub.buscarFilme(request);
        model.addAttribute("filme", response);
        return "home";
    }

    @PostMapping("/favoritar")
    public String favoritar(@RequestParam String imdbID) {
        grpcStub.marcarFavorito(FilmeServiceProto.FavoritoRequest.newBuilder().setImdbID(imdbID).build());
        return "redirect:/";
    }
}
