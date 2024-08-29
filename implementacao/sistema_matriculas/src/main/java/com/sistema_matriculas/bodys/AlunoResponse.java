package com.sistema_matriculas.bodys;

import java.util.List;

public record AlunoResponse(
        String matricula,
        String nome,
        String senha,
        List<DisciplinaResponse> disciplinasInscritas) {

}
