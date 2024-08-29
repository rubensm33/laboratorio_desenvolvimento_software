package com.sistema_matriculas.model;

import lombok.Data;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema_matriculas.services.TipoDisciplina;

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
    private Integer maxAlunos = 60;

    @Column(name = "minAlunos", nullable = true)
    private Integer minAlunos = 3;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_disciplina", nullable = false)
    private TipoDisciplina tipoDisciplina;
}
