package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Usuarios")
public class UserInfo {
    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;


    public UserInfo() {
    }

    public UserInfo(String nome, String email, String senha, String roles) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // getters e setters omitidos por brevidade
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
