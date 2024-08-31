package com.sistema_matriculas.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // Importação correta para Collectors

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_matriculas.bodys.AlunoResponse;
import com.sistema_matriculas.bodys.DisciplinaResponse;
import com.sistema_matriculas.model.Aluno;
import com.sistema_matriculas.model.Curriculo;
import com.sistema_matriculas.model.Disciplina;
import com.sistema_matriculas.repository.AlunoRepository;
import com.sistema_matriculas.repository.CurriculoRepository;
import com.sistema_matriculas.repository.DisciplinaRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private CurriculoRepository curriculoRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<AlunoResponse> getAllAlunos() {
        List<Aluno> allAlunos = alunoRepository.findAll();
        List<AlunoResponse> alunoResponses = new ArrayList<>();
        for (Aluno aluno : allAlunos) {
            alunoResponses.add(toAlunoResponse(aluno));
        }
        return alunoResponses;
    }

    @Transactional
    public ResponseEntity<AlunoResponse> inscreverEmDisciplinaPorNome(String matriculaAluno, String nomeDisciplina) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int semester = today.getMonthValue() > 6 ? 2 : 1;

        Optional<Curriculo> curriculoAtual = curriculoRepository.findByAnoAndSemestre(year, semester);
        if (curriculoAtual.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Disciplina> disciplina = curriculoAtual.get().getDisciplinas().stream()
                .filter(d -> d.getNome().equalsIgnoreCase(nomeDisciplina))
                .findFirst();

        if (disciplina.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (disciplina.get().getAlunosInscritos().size() >= disciplina.get().getMaxAlunos()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Aluno> aluno = alunoRepository.findById(matriculaAluno);
        if (aluno.isPresent()) {
            disciplina.get().getAlunosInscritos().add(aluno.get());
            aluno.get().getDisciplinasInscritas().add(disciplina.get());
            alunoRepository.save(aluno.get());
            disciplinaRepository.save(disciplina.get());

            AlunoResponse alunoResponse = toAlunoResponse(aluno.get());
            return ResponseEntity.ok(alunoResponse);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private AlunoResponse toAlunoResponse(Aluno aluno) {
        List<DisciplinaResponse> disciplinaResponses = DisciplinaResponse
                .toDisciplinaResponse(aluno.getDisciplinasInscritas());

        System.out.println(disciplinaResponses);
        return new AlunoResponse(
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getSenha(),
                disciplinaResponses);
    }
}
