package com.sistema_matriculas.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_matriculas.bodys.AlunoResponse;
import com.sistema_matriculas.model.Aluno;
import com.sistema_matriculas.model.Curriculo;
import com.sistema_matriculas.model.Disciplina;
import com.sistema_matriculas.model.Turma;
import com.sistema_matriculas.repository.AlunoRepository;
import com.sistema_matriculas.repository.CurriculoRepository;
import com.sistema_matriculas.repository.TurmaRepository;
import com.sistema_matriculas.utils.StatusTurma;
import com.sistema_matriculas.utils.TipoDisciplina;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    public List<AlunoResponse> getAllAlunos() {
        List<Aluno> allAlunos = alunoRepository.findAll();
        List<AlunoResponse> alunoResponses = new ArrayList<>();
        for (Aluno aluno : allAlunos) {
            alunoResponses.add(AlunoResponse.toAlunoResponse(aluno));
        }
        return alunoResponses;
    }

    @Transactional
    public ResponseEntity<?> inscreverEmDisciplinaPorNome(String matriculaAluno, String nomeDisciplina) {
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

        Optional<Aluno> aluno = alunoRepository.findById(matriculaAluno);
        if (aluno.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        long countObrigatorias = aluno.get().getDisciplinasInscritas().stream()
                .filter(d -> d.getTipoDisciplina() == TipoDisciplina.OBRIGATORIA)
                .count();
        long countOptativas = aluno.get().getDisciplinasInscritas().stream()
                .filter(d -> d.getTipoDisciplina() == TipoDisciplina.OPTATIVA)
                .count();

        if (disciplina.get().getTipoDisciplina() == TipoDisciplina.OBRIGATORIA && countObrigatorias >= 4 ||
                disciplina.get().getTipoDisciplina() == TipoDisciplina.OPTATIVA && countOptativas >= 2) {
            return ResponseEntity.badRequest().body("Limite de disciplinas " +
                    (disciplina.get().getTipoDisciplina() == TipoDisciplina.OBRIGATORIA ? "obrigatórias" : "optativas")
                    +
                    " atingido.");
        }

        Turma turma = turmaRepository.findByDisciplinaIdAndAnoAndSemestre(disciplina.get().getId(), year, semester)
                .orElseGet(() -> {
                    Turma newTurma = new Turma();
                    newTurma.setDisciplina(disciplina.get());
                    newTurma.setStatus(StatusTurma.PENDENTE);
                    newTurma.setAno(year);
                    newTurma.setSemestre(semester);
                    newTurma.setProfessor(disciplina.get().getProfessor());
                    return turmaRepository.save(newTurma);
                });

        if (!turma.getAlunos().contains(aluno.get())) {
            turma.getAlunos().add(aluno.get());
            turmaRepository.save(turma);
        }

        aluno.get().getDisciplinasInscritas().add(disciplina.get());
        alunoRepository.save(aluno.get());

        AlunoResponse alunoResponse = AlunoResponse.toAlunoResponse(aluno.get());
        return ResponseEntity.ok(alunoResponse);
    }
}
