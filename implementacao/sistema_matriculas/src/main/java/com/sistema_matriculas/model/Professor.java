package com.sistema_matriculas.model;

import lombok.Data;
import java.util.List;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "professor")
public class Professor extends Usuario {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "professor_disciplina",
        joinColumns = @JoinColumn(name = "professor_matricula", referencedColumnName = "matricula"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id", referencedColumnName = "_id")
    )
    private List<Disciplina> disciplinasLecionadas;
}
