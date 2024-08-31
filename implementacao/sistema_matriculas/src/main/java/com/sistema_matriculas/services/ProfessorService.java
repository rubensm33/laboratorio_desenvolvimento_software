package com.sistema_matriculas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema_matriculas.model.Professor;
import com.sistema_matriculas.model.Disciplina;
import com.sistema_matriculas.repository.ProfessorRepository;
import com.sistema_matriculas.repository.DisciplinaRepository;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Professor> getAllProfessores() {
        return professorRepository.findAll();
    }

    @Transactional
    public Professor adicionarDisciplina(String matriculaProfessor, Disciplina disciplina) {
        Optional<Professor> professorOptional = professorRepository.findById(matriculaProfessor);
        if (professorOptional.isPresent()) {
            Professor professor = professorOptional.get();
            if (!professor.getDisciplinasLecionadas().contains(disciplina)) {
                professor.getDisciplinasLecionadas().add(disciplina);
                disciplina.getProfessores().add(professor);
                disciplinaRepository.save(disciplina);
                return professorRepository.save(professor);
            }
        }
        return null;
    }

    public List<Disciplina> listarDisciplinas(String matriculaProfessor) {
        Optional<Professor> professorOptional = professorRepository.findById(matriculaProfessor);
        return professorOptional.map(Professor::getDisciplinasLecionadas).orElse(null);
    }
}