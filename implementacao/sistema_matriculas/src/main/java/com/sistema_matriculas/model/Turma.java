package com.sistema_matriculas.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.sistema_matriculas.utils.StatusTurma;

@Data
@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", referencedColumnName = "_id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "matricula")
    private Professor professor;

    @ManyToMany
    @JoinTable(name = "turma_aluno",
               joinColumns = @JoinColumn(name = "turma_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "aluno_matricula", referencedColumnName = "matricula"))
    private List<Aluno> alunos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusTurma status;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "semestre")
    private Integer semestre;
}
