package com.sistema_matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "com.sistema_matriculas")
public class SistemaMatriculasApplication {
    public static void main(String[] args) {
        SpringApplication.run(SistemaMatriculasApplication.class, args);
    }
}
