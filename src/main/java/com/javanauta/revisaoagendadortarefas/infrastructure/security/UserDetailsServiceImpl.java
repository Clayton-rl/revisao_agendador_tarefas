package com.javanauta.revisaoagendadortarefas.infrastructure.security;


import com.javanauta.revisaoagendadortarefas.business.dtos.UsuarioDTO;
import com.javanauta.revisaoagendadortarefas.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;


    public UserDetails carregaDadosUsuario(String email, String token) {

        UsuarioDTO dto = client.buscaUsuarioPorEmail(email, token);
        return User
                .withUsername(dto.getEmail())
                .password(dto.getSenha())
                .build();
    }
}
