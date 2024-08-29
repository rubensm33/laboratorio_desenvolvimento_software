/*package com.sistema_matriculas.model;

import lombok.Data;

import java.util.ArrayList;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private String matricula;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToMany
    @JoinColumn(name = "disciplinasLecionadas", nullable = true)
    private ArrayList<Disciplina> disciplinasLecionadas;
}

*/