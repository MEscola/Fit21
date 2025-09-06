package com.desafio.fit21.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.desafio.fit21.model.User;
import com.desafio.fit21.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserService userService;
    


    //CADASTRO USUÁRIO
    @PostMapping("/cadastro")
    public ResponseEntity<User> cadastro(@RequestBody User usuario) {
        User user = userService.cadastrar(usuario);
        return ResponseEntity.ok(user);
    }


    //LOGIN
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User usuario) {
        return userService.login(usuario.getEmail(), usuario.getSenha())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
    
    // Atualizar perfil
    @PutMapping("/{id}/perfil")
    public ResponseEntity<User> atualizarPerfil(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.atualizarPerfil(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    }

