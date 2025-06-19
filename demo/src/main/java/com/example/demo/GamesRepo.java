package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface GamesRepo extends MongoRepository<Games, String> {
    // Você pode adicionar métodos personalizados aqui, como:
    Games findByTitulo(String titulo);
}
