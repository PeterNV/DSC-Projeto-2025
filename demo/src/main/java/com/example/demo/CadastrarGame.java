package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CadastrarGame {
    @Autowired
    private GamesRepo repository;

    // @GetMapping("/cadastrar/{titulo}/{ano}/{classificacao}/{genero}")
    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody Games game) {
        Games gameBuscar = repository.findByTitulo(game.getTitulo().toUpperCase());
        //System.out.println(game);
        if (gameBuscar != null) {
            
            return "Nn";
        } else {
            repository.insert(new Games(game.getTitulo().toUpperCase(), game.getAno(), game.getGenero(), game.getClassificacao()));
        }
        

        return "Ss";
    }
}
