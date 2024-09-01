package com.sistema_matriculas.model;

import lombok.Data;
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
    // Método para adicionar um aluno à disciplina
    public void adicionarAluno(Aluno aluno) {
        this.alunosInscritos.add(aluno);
        aluno.getDisciplinasInscritas().add(this);
    }

    // Método para remover um aluno da disciplina
    public void removerAluno(Aluno aluno) {
        this.alunosInscritos.remove(aluno);
        aluno.getDisciplinasInscritas().remove(this);
    }

    // Método para adicionar uma turma à disciplina
    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
        turma.setDisciplina(this);
    }

    // Método para remover uma turma da disciplina
    public void removerTurma(Turma turma) {
        this.turmas.remove(turma);
        turma.setDisciplina(null);
    }
}

