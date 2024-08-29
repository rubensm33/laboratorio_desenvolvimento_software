package com.sistema_matriculas.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private String matricula;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "aluno_disciplina", joinColumns = @JoinColumn(name = "aluno_matricula", referencedColumnName = "matricula"), inverseJoinColumns = @JoinColumn(name = "disciplina__id", referencedColumnName = "_id"))
    private List<Disciplina> disciplinasInscritas;

}
