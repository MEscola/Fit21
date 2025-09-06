package com.desafio.fit21.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "progress")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;
    private int dayNumber;
    private boolean completed= false;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
}
