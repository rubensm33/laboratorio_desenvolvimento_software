package com.sistema_matriculas.model;

import lombok.Data;
import java.util.List;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "aluno")
public class Aluno extends Usuario {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "aluno_disciplina",
        joinColumns = @JoinColumn(name = "aluno_matricula", referencedColumnName = "matricula"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "_id")
    )
    private List<Disciplina> disciplinasInscritas;
}
