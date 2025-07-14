package com.example.demo.service;


import org.springframework.stereotype.Service;

@Service
public class AuditService {

    public void track(String message) {
        // Aqui você pode salvar em banco, enviar para fila, ou simplesmente logar
        System.out.println("[AUDIT] " + message);
    }
}
