package com.sistema_matriculas.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    // Construtor com parâmetros
    public Turma(Disciplina disciplina, Professor professor, StatusTurma status, Integer ano, Integer semestre) {
        this.disciplina = disciplina;
        this.professor = professor;
        this.status = status;
        this.ano = ano;
        this.semestre = semestre;
    }

    // Métodos equals e hashCode para comparar instâncias da classe
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turma turma = (Turma) o;
        return Objects.equals(id, turma.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Método toString para representação em String
    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", disciplina=" + disciplina +
                ", professor=" + professor +
                ", status=" + status +
                ", ano=" + ano +
                ", semestre=" + semestre +
                '}';
    }
}

