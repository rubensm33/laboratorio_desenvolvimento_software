package com.sistema_matriculas.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_matriculas.model.Curriculo;
import com.sistema_matriculas.model.Disciplina;
import com.sistema_matriculas.repository.AlunoRepository;
import com.sistema_matriculas.repository.CurriculoRepository;
import com.sistema_matriculas.repository.DisciplinaRepository;
import com.sistema_matriculas.utils.TipoDisciplina;

@Service
public class SecretariaService {

    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final CurriculoRepository curriculoRepository;

    public SecretariaService(AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository,
            CurriculoRepository curriculoRepository) {
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.curriculoRepository = curriculoRepository;
    }

    @Transactional
    public Curriculo criarCurriculo() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int semester = (today.getMonthValue() > 6) ? 2 : 1;

        Optional<Curriculo> existingCurriculo = curriculoRepository.findByAnoAndSemestre(year, semester);
        if (existingCurriculo.isPresent()) {
            return existingCurriculo.get();
        } else {
            Curriculo newCurriculo = new Curriculo();
            newCurriculo.setAno(year);
            newCurriculo.setSemestre(semester);
            return curriculoRepository.save(newCurriculo);
        }
    }

    @Transactional
    public Disciplina adicionarDisciplinaAoCurriculo(String nome, Integer creditos, TipoDisciplina tipoDisciplina) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int semester = (today.getMonthValue() > 6) ? 2 : 1;

        Curriculo curriculo = curriculoRepository.findByAnoAndSemestre(year, semester)
                .orElseGet(() -> criarCurriculo());

        Disciplina novaDisciplina = new Disciplina();
        novaDisciplina.setNome(nome);
        novaDisciplina.setCreditos(creditos);
        novaDisciplina.setTipoDisciplina(tipoDisciplina);
        novaDisciplina.setMaxAlunos(60);
        novaDisciplina.setMinAlunos(3);

        disciplinaRepository.save(novaDisciplina);

        curriculo.getDisciplinas().add(novaDisciplina);
        curriculoRepository.save(curriculo);

        return novaDisciplina;
    }
}
