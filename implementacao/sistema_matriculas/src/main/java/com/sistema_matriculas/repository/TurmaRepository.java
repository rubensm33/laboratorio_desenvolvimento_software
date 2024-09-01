package com.sistema_matriculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sistema_matriculas.model.Turma;

import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, String> {
    Optional<Turma> findByDisciplinaIdAndAnoAndSemestre(String disciplinaId, Integer ano, Integer semestre);
}
