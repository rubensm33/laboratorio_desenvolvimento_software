package com.matriculas.services;

import java.util.ArrayList;

public class Curso {
    private String nome;
    private Integer creditos;
    private ArrayList<Disciplina> disciplinasCurso;

    public Curso(String nome, Integer creditos) {
        this.nome = nome;
        this.creditos = creditos;
    }

    public void adicionarDisciplina(Disciplina disciplina) {

    }

}
