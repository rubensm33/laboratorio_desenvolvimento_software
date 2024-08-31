package com.sistema_matriculas.model;

import lombok.Data;

import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema_matriculas.utils.TipoDisciplina;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private String id;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "professor_disciplina",
        joinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "_id"),
        inverseJoinColumns = @JoinColumn(name = "professor_matricula", referencedColumnName = "matricula")
    )
    private List<Professor> professores;
}