package com.example.demo.model;

public class AuthRequest {
    private String email;
    private String senha;

    public AuthRequest() {
    }

    public AuthRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // getters e setters
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
}