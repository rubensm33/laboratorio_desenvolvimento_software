package com.sistema_matriculas.model;

import java.util.List;

import com.sistema_matriculas.utils.TipoDisciplina;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "creditos", nullable = false)
    private Integer creditos;

    @Column(name = "maxAlunos", nullable = false)
    private Integer maxAlunos;

    @Column(name = "minAlunos", nullable = false)
    private Integer minAlunos;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_disciplina", nullable = false)
    private TipoDisciplina tipoDisciplina;

    @ManyToMany(mappedBy = "disciplinasInscritas")
    private List<Aluno> alunosInscritos;

    @OneToMany(mappedBy = "disciplina")
    private List<Turma> turmas;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso; // Adicionando a associação com Curso
    
    // Métodos para adicionar e remover alunos e turmas omitidos para brevidade

    public String getNome() {
        return nome;
    }

    public TipoDisciplina getTipoDisciplina() {
        return tipoDisciplina;
    }

    public Long getId() {
        return id;
    }

    public Professor getProfessor() {
        return professor;
    }
}