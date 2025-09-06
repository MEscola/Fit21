package com.desafio.fit21.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer idade;
    private String email;
    private String senha; //todo: encrypt
    private Double peso;
    private Double altura;
    private String metaTreino; // hipertrofia, emagrecimento

    private Integer pontuacao = 0; //pontos para conquistas

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL) //

    private List<Progress> progresso;

}
