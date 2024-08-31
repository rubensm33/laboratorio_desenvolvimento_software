package com.sistema_matriculas.repository;

import com.sistema_matriculas.model.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Secretariaepository extends JpaRepository<Secretaria, String> {}
