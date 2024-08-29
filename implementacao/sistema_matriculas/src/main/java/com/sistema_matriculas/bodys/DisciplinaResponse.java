package com.sistema_matriculas.bodys;

import java.util.List;
import java.util.stream.Collectors;

import com.sistema_matriculas.model.Disciplina;
import com.sistema_matriculas.services.TipoDisciplina;

public record DisciplinaResponse(
        String id,
        String nome,
        Integer creditos,
        Integer maximoAlunos,
        Integer minimoAlunos,
        TipoDisciplina tipoDisciplina) {

    public static List<DisciplinaResponse> toDisciplinaResponse(List<Disciplina> disciplinas) {
        return disciplinas.stream()
                .map(disciplina -> new DisciplinaResponse(
                        disciplina.getId(),
                        disciplina.getNome(),
                        disciplina.getCreditos(),
                        disciplina.getMaxAlunos(),
                        disciplina.getMinAlunos(),
                        disciplina.getTipoDisciplina()))  // <- Corrigi aqui, adicionei uma vírgula
                .collect(Collectors.toList());
    }
}
