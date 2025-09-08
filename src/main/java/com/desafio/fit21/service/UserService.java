package com.desafio.fit21.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.fit21.model.User;
import com.desafio.fit21.repository.UsuarioRepository;
import com.desafio.fit21.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    // CADASTRO USUÁRIO
    public User cadastrar(User usuario) {

        //IMPLEMENTAR HASH DE SENHA
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    // Login
    public Optional<String> login(String email, String senha) {
    return usuarioRepository.findByEmail(email)
            .filter(u -> passwordEncoder.matches(senha, u.getSenha()))
            .map(u -> jwtUtil.generateToken(u.getEmail()));
}


    


    // Atualizar perfil
    public User atualizarPerfil(Long id, User usuarioAtualizado) {
        User u = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        u.setNome(usuarioAtualizado.getNome());
        u.setIdade(usuarioAtualizado.getIdade());
        u.setPeso(usuarioAtualizado.getPeso());
        u.setAltura(usuarioAtualizado.getAltura());
        u.setMetaTreino(usuarioAtualizado.getMetaTreino());
        return usuarioRepository.save(u);
    }
}
