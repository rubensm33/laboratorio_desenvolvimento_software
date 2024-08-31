package com.sistema_matriculas.controller;

import com.sistema_matriculas.model.Disciplina;
import com.sistema_matriculas.model.Professor;
import com.sistema_matriculas.services.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
@Tag(name = "Professor", description = "Operações relacionadas aos professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    @Operation(summary = "Listar todos os professores", description = "Retorna uma lista de todos os professores cadastrados")
    public ResponseEntity<List<Professor>> getAllProfessores() {
        List<Professor> professores = professorService.getAllProfessores();
        return ResponseEntity.ok(professores);
    }

    @PostMapping("/adicionarDisciplina")
    @Operation(summary = "Adicionar disciplina ao professor", description = "Adiciona uma disciplina ao professor pelo ID")
    public ResponseEntity<Professor> adicionarDisciplina(
            @RequestParam String matriculaProfessor,
            @RequestBody Disciplina disciplina) {

        Professor professor = professorService.adicionarDisciplina(matriculaProfessor, disciplina);
        if (professor != null) {
            return ResponseEntity.ok(professor);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{matricula}/disciplinas")
    @Operation(summary = "Listar disciplinas do professor", description = "Lista todas as disciplinas que o professor leciona")
    public ResponseEntity<List<Disciplina>> listarDisciplinas(@PathVariable String matricula) {
        List<Disciplina> disciplinas = professorService.listarDisciplinas(matricula);
        if (disciplinas != null) {
            return ResponseEntity.ok(disciplinas);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}