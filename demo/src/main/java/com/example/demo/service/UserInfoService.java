package com.example.demo.service;

import com.example.demo.model.UserInfo;
import com.example.demo.model.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepo repository;

    @Autowired
    private PasswordEncoder encoder;

    public String addUser(UserInfo userInfo) {

        userInfo.setSenha(encoder.encode(userInfo.getSenha()));
        repository.save(userInfo);
        return "Usuário cadastrado com sucesso";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo user = repository.findByEmail(email);
        if (user == null) {
            
            System.out.println("Usuário não encontrado");
            throw new UsernameNotFoundException("Usuário não encontrado");
            
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getSenha(),
                java.util.Collections.emptyList());
    }
}
