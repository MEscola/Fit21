package com.desafio.fit21.controller;


import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.fit21.model.User;
import com.desafio.fit21.security.JwtUtil;
import com.desafio.fit21.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserService userService;
    
    private final JwtUtil jwtUtil;
    


    //CADASTRO USUÁRIO
    @PostMapping("/cadastro")
    public ResponseEntity<User> cadastro(@RequestBody User usuario) {
        User user = userService.cadastrar(usuario);
        return ResponseEntity.ok(user);
    }


    
    // LOGIN -> retorna JWT + usuarioId + nome
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User usuario) {
        return userService.login(usuario.getEmail(), usuario.getSenha())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).body("Credenciais inválidas"));
    }
    
    // Atualizar perfil
    @PutMapping("/{id}/perfil")
    public ResponseEntity<User> atualizarPerfil(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.atualizarPerfil(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    }

