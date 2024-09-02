package com.sistema_matriculas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema_matriculas.services.AlunoService;
import com.sistema_matriculas.services.SecretariaService;
import com.sistema_matriculas.bodys.AlunoResponse;
import com.sistema_matriculas.model.Aluno;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/aluno")
@Tag(name = "Aluno", description = "Operações relacionadas aos alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final SecretariaService secretariaService;


    public AlunoController(AlunoService alunoService, SecretariaService secretariaService) {
        this.alunoService = alunoService;
        this.secretariaService = secretariaService;
    }

    @GetMapping("/all")
    @Operation(summary = "Listar todos os alunos", description = "Retorna uma lista de todos os alunos cadastrados")
    public ResponseEntity<List<AlunoResponse>> getAllAlunos() {
        List<AlunoResponse> alunos = alunoService.getAllAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @PostMapping("/inscrever/{matricula}/disciplina")
    public ResponseEntity<?> inscreverEmDisciplinaPorNome(
            @PathVariable String matricula,
            @RequestParam String nomeDisciplina) {

        return alunoService.inscreverEmDisciplinaPorNome(matricula, nomeDisciplina);
    }

    @PostMapping("/matricular")
    public ResponseEntity<Aluno> matricularAluno(@RequestParam String nome, @RequestParam String senha) {
        Aluno aluno = secretariaService.matricularAlunoUniversidade(nome, senha);
        return ResponseEntity.ok(aluno);
    }
}
