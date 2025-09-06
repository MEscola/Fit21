package com.desafio.fit21.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.fit21.model.User;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndSenha(String email, String senha);

    //Optional<User> findById(Long id);
}

//Optional- como você trata o caso em que o usuário não existe ou a senha está errada.