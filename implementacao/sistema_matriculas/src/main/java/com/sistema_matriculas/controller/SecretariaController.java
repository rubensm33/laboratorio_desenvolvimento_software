package com.sistema_matriculas.controller;

import com.sistema_matriculas.model.Curriculo;
import com.sistema_matriculas.model.Disciplina;
import com.sistema_matriculas.model.Professor;
import com.sistema_matriculas.services.SecretariaService;
import com.sistema_matriculas.utils.TipoDisciplina;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Tag(name = "Secretaria", description = "Operações relacionadas a secretaria")
@RequestMapping("/api/secretaria")
public class SecretariaController {

    private final SecretariaService secretariaService;

    public SecretariaController(SecretariaService secretariaService) {
        this.secretariaService = secretariaService;
    }

    @PostMapping("/criarCurriculo")
    public ResponseEntity<Curriculo> criarCurriculo() {
        Curriculo curriculo = secretariaService.criarCurriculo();
        return ResponseEntity.ok(curriculo);
    }

    @PostMapping("/adicionarDisciplina")
    public ResponseEntity<Disciplina> adicionarDisciplina(
            @RequestParam String nome,
            @RequestParam Integer creditos,
            @Parameter(description = "Tipo de Disciplina", required = true, example = "OBRIGATORIA") @RequestParam TipoDisciplina tipoDisciplina) {
        Disciplina disciplina = secretariaService.adicionarDisciplinaAoCurriculo(nome, creditos, tipoDisciplina);
        return ResponseEntity.ok(disciplina);
    }

    @PostMapping("/validarTurma/{turmaId}")
    public ResponseEntity<String> validarTurma(@PathVariable Long turmaId) {
        return secretariaService.validarTurma(turmaId);
    }

    @PostMapping("/validarTodasTurmas")
    public ResponseEntity<String> validarTodasTurmas() {
        return secretariaService.validarTodasTurmas();
    }

    @PostMapping("/matricularProfessor")
    public ResponseEntity<Professor> matricularProfessor(@RequestBody Professor professor) {
        Professor novoProfessor = secretariaService.criarProfessor(professor);
        return ResponseEntity.ok(novoProfessor);
    }

    @PutMapping("/associarProfessor")
    public ResponseEntity<Disciplina> associarProfessorADisciplina(
            @RequestParam String professorMatricula,
            @RequestParam String disciplinaId) throws IllegalStateException {
        try {
            Disciplina disciplina = secretariaService.associarProfessorADisciplina(professorMatricula, disciplinaId);
            return ResponseEntity.ok(disciplina);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
