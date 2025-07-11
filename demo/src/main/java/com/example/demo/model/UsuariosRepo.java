package com.example.demo.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuariosRepo extends MongoRepository<Usuarios, String> {
    Usuarios findByEmail(String email);
}