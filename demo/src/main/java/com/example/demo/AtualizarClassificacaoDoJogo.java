package com.example.demo;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AtualizarClassificacaoDoJogo {
      @Autowired
    private GamesRepo repository;
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
}
