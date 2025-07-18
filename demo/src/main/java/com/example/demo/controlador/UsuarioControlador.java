package com.example.demo.controlador;

//import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import com.example.demo.model.AuthRequest;
//import com.example.demo.model.UserInfo;
import com.example.demo.service.AuditService;
import com.example.demo.service.JwtService;
//import com.example.demo.service.UserInfoService;

import jakarta.validation.Valid;

//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.model.Usuarios;
import com.example.demo.model.UsuariosRepo;

@RestController
@RequestMapping("/auth")
public class UsuarioControlador {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuariosRepo repository;

    //@Autowired
    //private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuditService auditService;

    @PostMapping("/cadastrarUser")
    public ResponseEntity<String> cadastrar(@RequestBody @Valid Usuarios usuario) {
        if (repository.findByEmail(usuario.getEmail()) != null) {
            return ResponseEntity.status(409).body("Nn"); // Já existe
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.insert(usuario);
        return ResponseEntity.ok("Ss");
    }

    @PutMapping("/atualizarUser")
    public ResponseEntity<String> atualizar(@RequestBody @Valid Usuarios usuario) {
        Usuarios usuarioExistente = repository.findByEmail(usuario.getEmail());
        if (usuarioExistente == null) {
            return ResponseEntity.status(404).body("Nn"); //Não encontrado
        }
        usuarioExistente.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuarioExistente);
        return ResponseEntity.ok("Ss");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody @Valid LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha())
        );

        if (authentication.isAuthenticated()) {
            auditService.track("Logout from: " + authentication.getName());
        } else {
            auditService.track("Logout attempt with no authentication");
        }

        return ResponseEntity.ok("Logout realizado com sucesso.");
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody @Valid LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginDTO.getEmail());
            return ResponseEntity.ok("Ss" + token);
        } else {
            throw new UsernameNotFoundException("Login inválido!");
        }
    }
}
