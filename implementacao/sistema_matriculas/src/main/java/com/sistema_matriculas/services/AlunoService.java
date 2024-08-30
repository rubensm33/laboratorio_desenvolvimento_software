package com.sistema_matriculas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; // Importação correta para Collectors

import org.springframework.stereotype.Service;

import com.sistema_matriculas.bodys.AlunoResponse;
import com.sistema_matriculas.bodys.DisciplinaResponse;
import com.sistema_matriculas.model.Aluno;
import com.sistema_matriculas.repository.AlunoRepository;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoResponse> getAllAlunos() {
        List<Aluno> allAlunos = alunoRepository.findAll();
        List<AlunoResponse> alunoResponses = new ArrayList<>();
        for (Aluno aluno : allAlunos) {
            alunoResponses.add(toAlunoResponse(aluno));
        }
        return alunoResponses;
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
