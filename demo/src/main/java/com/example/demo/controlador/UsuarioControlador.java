package com.example.demo.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Usuarios;
import com.example.demo.model.UsuariosRepo;

@RestController
public class UsuarioControlador {

    @Autowired
    private UsuariosRepo repository;

    @PostMapping("/cadastrarUser")
    public String cadastrar(@RequestBody Usuarios usuario) {
        Usuarios usuarioBuscar = repository.findByEmail(usuario.getEmail());
        if (usuarioBuscar != null) {
            return "Nn"; // Já existe
        } else {
            repository.insert(new Usuarios(usuario.getNome(), usuario.getEmail(), usuario.getSenha()));
            return "Ss"; // Sucesso
        }
    }

    @PutMapping("/atualizarUser")
    public String atualizar(@RequestBody Usuarios usuario) {
        Usuarios usuarioBuscar = repository.findByEmail(usuario.getEmail());
        if (usuarioBuscar != null) {
            usuarioBuscar.setSenha(usuario.getSenha());
            repository.save(usuarioBuscar);
            return "Ss"; // Atualizado com sucesso
        } else {
            return "Nn"; // Não encontrado
        }
    }
}
