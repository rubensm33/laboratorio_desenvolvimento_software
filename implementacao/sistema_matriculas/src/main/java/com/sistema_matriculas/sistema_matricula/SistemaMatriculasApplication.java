package com.sistema_matriculas.sistema_matricula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SistemaMatriculasApplication {
    public static void main(String[] args) {
        SpringApplication.run(SistemaMatriculasApplication.class, args);
    }
}
