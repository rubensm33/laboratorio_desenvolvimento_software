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
    private Long matricula;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "aluno_disciplina", joinColumns = @JoinColumn(name = "aluno_matricula", referencedColumnName = "matricula"), inverseJoinColumns = @JoinColumn(name = "disciplina__id", referencedColumnName = "_id"))
    private List<Disciplina> disciplinasInscritas;

     // Construtor com argumentos para nome e senha
    public Aluno(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.disciplinasInscritas = new ArrayList<>();
    }

    // Método para matricular o aluno em uma disciplina
    public void matricularDisciplina(Disciplina disciplina) {
        if (!disciplinasInscritas.contains(disciplina)) {
            disciplinasInscritas.add(disciplina);
            disciplina.getAlunosInscritos().add(this); // Adiciona a referência bidirecional
        }
    }

    // Método para cancelar a matrícula do aluno em uma disciplina
    public void cancelarDisciplina(Disciplina disciplina) {
        if (disciplinasInscritas.contains(disciplina)) {
            disciplinasInscritas.remove(disciplina);
            disciplina.getAlunosInscritos().remove(this); // Remove a referência bidirecional
        }
    }

    // Método para consultar se o aluno está matriculado em uma disciplina específica
    public boolean consultarDisciplina(Disciplina disciplina) {
        return disciplinasInscritas.contains(disciplina);
    }

    // Método para pagar cobrança (simulação)
    public void pagarCobranca() {
        System.out.println("Cobrança paga com sucesso para o aluno " + nome);
    }
}