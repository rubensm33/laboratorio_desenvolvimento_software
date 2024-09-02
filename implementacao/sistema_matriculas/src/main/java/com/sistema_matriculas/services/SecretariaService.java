package com.sistema_matriculas.services;

import java.time.LocalDate;
import java.util.Optional;

import org.hibernate.mapping.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_matriculas.model.Aluno;
import com.sistema_matriculas.model.Curriculo;
import com.sistema_matriculas.model.Disciplina;
import com.sistema_matriculas.model.Professor;
import com.sistema_matriculas.model.Turma;
import com.sistema_matriculas.repository.AlunoRepository;
import com.sistema_matriculas.repository.CurriculoRepository;
import com.sistema_matriculas.repository.DisciplinaRepository;
import com.sistema_matriculas.repository.ProfessorRepository;
import com.sistema_matriculas.repository.TurmaRepository;
import com.sistema_matriculas.utils.StatusTurma;
import com.sistema_matriculas.utils.TipoDisciplina;

@Service
public class SecretariaService {

    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final CurriculoRepository curriculoRepository;
    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;

    public SecretariaService(AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository,
            CurriculoRepository curriculoRepository, ProfessorRepository professorRepository,
            TurmaRepository turmaRepository) {
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
        this.curriculoRepository = curriculoRepository;
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
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

    @Transactional
    public Disciplina associarProfessorADisciplina(String professorMatricula, String disciplinaId) {
        Professor professor = professorRepository.findById(professorMatricula)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int semester = (today.getMonthValue() > 6) ? 2 : 1;

        Optional<Curriculo> curriculoAtual = curriculoRepository.findByAnoAndSemestre(year, semester);
        if (curriculoAtual.isEmpty() || !curriculoAtual.get().getDisciplinas().contains(disciplina)) {
            throw new IllegalStateException("A disciplina não está inscrita no currículo atual.");
        }

        if (disciplina.getProfessor() != null) {
            throw new IllegalStateException("Já existe um professor associado a esta disciplina no currículo atual.");
        }

        disciplina.setProfessor(professor);
        return disciplinaRepository.save(disciplina);
    }

    public ResponseEntity<String> validarTurma(Long turmaId) {
        Optional<Turma> turmaOptional = turmaRepository.findById(turmaId);
        if (!turmaOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Turma não encontrada!");
        }

        Turma turma = turmaOptional.get();
        if (turma.getAlunos().size() < 3) {
            turma.setStatus(StatusTurma.CANCELADA);
        } else {
            turma.setStatus(StatusTurma.ATIVO);
        }
        turmaRepository.save(turma);

        return ResponseEntity
                .ok("Turma " + (turma.getStatus() == StatusTurma.ATIVO ? "ativada" : "cancelada") + " com sucesso.");
    }

    public ResponseEntity<String> validarTodasTurmas() {
        java.util.List<Turma> turmas = turmaRepository.findAll();
        int turmasAtivadas = 0, turmasCanceladas = 0;

        for (Turma turma : turmas) {
            int numAlunos = turma.getAlunos().size();
            StatusTurma statusAnterior = turma.getStatus();
            if (numAlunos < 3) {
                turma.setStatus(StatusTurma.CANCELADA);
                if (statusAnterior != StatusTurma.CANCELADA)
                    turmasCanceladas++;
            } else {
                turma.setStatus(StatusTurma.ATIVO);
                if (statusAnterior != StatusTurma.ATIVO)
                    turmasAtivadas++;
            }
            turmaRepository.save(turma);
        }

        return ResponseEntity
                .ok("Turmas atualizadas. Ativadas: " + turmasAtivadas + ", Canceladas: " + turmasCanceladas + ".");
    }

    @Transactional
    public Aluno matricularAlunoUniversidade(String nome, String senha) {
        Aluno novoAluno = new Aluno();
        novoAluno.setNome(nome);
        novoAluno.setSenha(senha);
        return alunoRepository.save(novoAluno);
    }

    
    @Transactional
    public Professor criarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

}
