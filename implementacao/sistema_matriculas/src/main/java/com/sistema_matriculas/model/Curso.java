package com.sistema_matriculas.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @OneToMany(mappedBy = "curso")
    private List<Disciplina> disciplinas;
}
