package com.sistema_matriculas.services;

import java.util.ArrayList;
import java.util.List;

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
        List<AlunoResponse> alunoResponse = new ArrayList<>();
        for (Aluno aluno : allAlunos) {
            alunoResponse.add(toAlunoResponse(aluno));
        }
        return alunoResponse;
    }

    private AlunoResponse toAlunoResponse(Aluno aluno) {
        List<DisciplinaResponse> disciplina = DisciplinaResponse.toDisciplinaResponse(aluno.getDisciplinasInscritas());
        return new AlunoResponse(
                aluno.getMatricula(),
                aluno.getNome(),
                aluno.getSenha(),
                disciplina);
    }
}
