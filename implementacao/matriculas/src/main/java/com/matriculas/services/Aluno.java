package com.matriculas.services;

import java.util.ArrayList;

public class Aluno extends Usuario {
    private String matricula;
    private ArrayList<Disciplina> disciplinasInscritas;

    public Aluno(String nome, String login, String senha, String matricula) {
        super(nome, login, senha);
        this.matricula = matricula;
    }

    public void matricularDisciplina(Disciplina disciplina) {
    }

    public void cancelarDisciplina(Disciplina disciplina) {
    }

    public void consultarDisciplina(Disciplina disciplina) {

    }

    public void pagarCobranca() {
    }
}
