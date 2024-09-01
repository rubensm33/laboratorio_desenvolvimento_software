package com.sistema_matriculas.model;

import lombok.Data;
import jakarta.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "curriculo")
public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    private Long id; 

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "semestre", nullable = false)
    private Integer semestre;

    @ManyToMany
    @JoinTable(name = "curriculo_disciplina", joinColumns = @JoinColumn(name = "curriculo_id", referencedColumnName = "_id"), inverseJoinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "_id"))
    private List<Disciplina> disciplinas;
    // Construtor personalizado
    public Curriculo(Integer ano, Integer semestre) {
        this.ano = ano;
        this.semestre = semestre;
    }

    // Método para adicionar uma disciplina ao currículo
    public void adicionarDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    // Método para remover uma disciplina do currículo
    public void removerDisciplina(Disciplina disciplina) {
        disciplinas.remove(disciplina);
    }

    // Método para verificar se uma disciplina está no currículo
    public boolean contemDisciplina(Disciplina disciplina) {
        return disciplinas.contains(disciplina);
    }

    // Método para obter o número total de disciplinas no currículo
    public int getTotalDisciplinas() {
        return disciplinas.size();
    }
}

