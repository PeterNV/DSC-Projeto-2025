package com.example.demo.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Games;
import com.example.demo.model.GamesRepo;

@RestController
@RequestMapping("/auth")
public class GameControlador {
    @Autowired
    private GamesRepo repository;
    public static String status;

    // @GetMapping("/cadastrar/{titulo}/{ano}/{classificacao}/{genero}")
    @PostMapping("/cadastrar")

    public String cadastrar(@RequestBody Games game) {
        Games gameBuscar = repository.findByTitulo(game.getTitulo().toUpperCase());
        // System.out.println(game);
        if (gameBuscar != null) {

            return "Nn";
        } else {
            repository.insert(new Games(game.getTitulo().toUpperCase(), game.getAno(), game.getGenero(),
                    game.getClassificacao()));
            return "Ss";
        }

    }

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

    @PutMapping("/atualizar")
    public String atualizar(@RequestBody Games game) {

        Games gameBuscar = repository.findByTitulo(game.getTitulo().toUpperCase());

        System.out.println(gameBuscar);
        if (gameBuscar != null) {

            gameBuscar.setClassificacao(game.getClassificacao());
            repository.save(gameBuscar);

        } else {
            return "Nn";
        }
        return "Ss";
    }

    @GetMapping("/buscar/{titulo}")
    public Games RetornaGame(@PathVariable String titulo) {
        Games game = repository.findByTitulo(titulo.toUpperCase());

        if (game != null) {
            return game;
        } else {
            return null;
        }
    }

    @GetMapping("/VerGame")
    public List<Games> ExibirTodos() {
        // MongoDBCompleto rertornarLista = new MongoDBCompleto();
        // rertornarLista.retornaLista();
        // return rertornarLista.retornaLista();
        /*
         * for (Games g : repository.findAll()) {
         * System.out.println(g.getTitulo() + " - " + g.getAno());
         * Resultado += "<tr><td>" + g.getTitulo().replace("%20", " ").toUpperCase() +
         * "</td>" + "<td>" + g.getAno() + "</td>" + "<td>"
         * + g.getClassificacao() + "</td>" + "<td>" + g.getGenero().replace("%20", " ")
         * + "</td>" + "</tr>";
         * }
         * return
         * "<style> table{border: 3px solid black; text-align: center;} td{border: 3px solid black;} th{border: 3px solid black;}</style><table><tr><th>TÍTULO</th><th>ANO</th><th>CLASSIFICAÇÃO</th><th>GENÊRO</th></tr>"
         * + Resultado + "</table>";
         */
        return repository.findAll();
    }
}
