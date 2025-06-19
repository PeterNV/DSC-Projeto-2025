package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class DeletarGameDaLista {
    @Autowired
    private GamesRepo repository;
    public static String status;

    @DeleteMapping("/deletar/{titulo}")
    public String deletar(@PathVariable String titulo) {
        // MongoDBCompleto deletar = new MongoDBCompleto();
    
       Games game = repository.findByTitulo(titulo.toUpperCase());
       System.out.println(game);
    if (game != null) {
        repository.delete(game);
        return "Ss";
    } else {
        return "Nn";
    }
    }
}
