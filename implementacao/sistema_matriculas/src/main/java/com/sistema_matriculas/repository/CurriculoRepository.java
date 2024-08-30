package com.sistema_matriculas.repository;

import com.sistema_matriculas.model.Curriculo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, String> {

    Optional<Curriculo> findByAnoAndSemestre(Integer ano, Integer semestre);

}
