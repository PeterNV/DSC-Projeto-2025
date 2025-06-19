package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuscarApenasUm {
    @Autowired
    private GamesRepo repository;
    @GetMapping("/buscar/{titulo}")
    public String RetornaGame(@PathVariable String titulo){
        Games game = repository.findByTitulo(titulo.toUpperCase());
      
        if(game != null){
            return game.getTitulo()+"<br>"+game.getAno()+"<br>"+game.getClassificacao()+"<br>"+game.getGenero();
        }else{
            return "Jogo não encontrado.";
        }
    }
}
