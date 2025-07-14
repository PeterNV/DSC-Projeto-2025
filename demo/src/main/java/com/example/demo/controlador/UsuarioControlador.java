package com.example.demo.controlador;

//import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.AuthRequest;
import com.example.demo.model.UserInfo;
import com.example.demo.service.AuditService;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserInfoService;

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
    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private AuditService auditService;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    @PostMapping("/cadastrarUser")
    public String cadastrar(@RequestBody Usuarios usuario) {
        Usuarios usuarioBuscar = repository.findByEmail(usuario.getEmail());
        if (usuarioBuscar != null) {
            return "Nn"; // Já existe
        } else {
            // ⚠️ Codificar a senha manualmente
            usuario.setSenha(encoder.encode(usuario.getSenha()));
            repository.insert(usuario);
            return "Ss";
        }
    }

    @PutMapping("/atualizarUser")
    public String atualizar(@RequestBody Usuarios usuario) {
        Usuarios usuarioBuscar = repository.findByEmail(usuario.getEmail());
        if (usuarioBuscar != null) {
            usuarioBuscar.setSenha(encoder.encode(usuario.getSenha()));
            repository.save(usuarioBuscar);
            return "Ss"; // Atualizado com sucesso
        } else {
            return "Nn"; // Não encontrado
        }
    }

    /*
     * @PostMapping("/login")
     * public String login(@RequestBody LoginDTO loginDTO) {
     * Usuarios usuario = repository.findByEmail(loginDTO.getEmail());
     * 
     * if (usuario != null && usuario.getSenha().equals(loginDTO.getSenha())) {
     * return "Ss"; // Sucesso
     * } else {
     * return "Nn"; // Falha no login
     * }
     * }
     */
    @PostMapping("/logout")
    public String logout(@RequestBody AuthRequest authRequest) {
       
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getSenha()));
        if (authentication != null && authentication.isAuthenticated()) {
            auditService.track("Logout from: User: " + authentication.getName());
        } else {
            auditService.track("Logout attempt with no authentication  " );
        }

        return "Logout realizado com sucesso.";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getSenha()));
        if (authentication.isAuthenticated()) {
            return "Ss" + jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("Login inválido!");

        }

    }
}
