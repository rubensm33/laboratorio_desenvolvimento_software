package com.sistema_matriculas.model;

import lombok.Data;

import java.util.Objects;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private Long matricula;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;

        // Construtor com parâmetros
    public Professor(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    // Métodos equals e hashCode para comparar instâncias da classe
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(matricula, professor.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    // Método toString para representação em String
    @Override
    public String toString() {
        return "Professor{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}



