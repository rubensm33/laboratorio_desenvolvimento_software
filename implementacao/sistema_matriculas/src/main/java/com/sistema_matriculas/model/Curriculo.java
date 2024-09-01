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
}
