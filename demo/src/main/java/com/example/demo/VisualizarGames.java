package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisualizarGames {
    //private static String Resultado;
    @Autowired
    private GamesRepo repository;

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
