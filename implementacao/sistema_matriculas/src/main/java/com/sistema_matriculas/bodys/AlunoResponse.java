package com.sistema_matriculas.bodys;

import java.util.List;

import com.sistema_matriculas.model.Aluno;

public record AlunoResponse(
                String matricula,
                String nome,
                String senha,
                List<DisciplinaResponse> disciplinasInscritas) {
        public static AlunoResponse toAlunoResponse(Aluno aluno) {
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
