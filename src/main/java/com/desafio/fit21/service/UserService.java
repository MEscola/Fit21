package com.desafio.fit21.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.desafio.fit21.model.User;
import com.desafio.fit21.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UsuarioRepository usuarioRepository;

    // CADASTRO USUÁRIO
    public User cadastrar(User usuario) {
        //TODO: IMPLEMENTAR HASH DE SENHA

        return usuarioRepository.save(usuario);
    }

    // Login
    public Optional<User> login(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha)
                .filter(u -> u.getSenha().equals(senha)); // TODO: usar hash
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
