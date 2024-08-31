package com.sistema_matriculas.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "secretaria")
public class Secretaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricula")
    private String matricula;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;
}
