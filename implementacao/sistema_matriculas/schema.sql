CREATE DATABASE sistema_matriculas IF NOT EXISTS;

USE sistema_matriculas;

CREATE TABLE professor (
    matricula INT PRIMARY KEY,
    nome VARCHAR(255),
    senha VARCHAR(255)
);

CREATE TABLE disciplina (
    _id INT PRIMARY KEY,
    nome VARCHAR(255),
    creditos INT,
    maxAlunos INT,
    minAlunos INT,
    tipo_disciplina ENUM('OBRIGATORIA', 'OPTATIVA'),
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES professor(matricula)
);

CREATE TABLE turma (
    id INT PRIMARY KEY,
    disciplina_id INT,
    professor_id INT,
    status ENUM('ATIVA', 'PENDENTE', 'CANCELADA'),
    ano INT,
    semestre INT,
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(_id),
    FOREIGN KEY (professor_id) REFERENCES professor(matricula)
);

CREATE TABLE aluno (
    matricula INT PRIMARY KEY,
    nome VARCHAR(255),
    senha VARCHAR(255)
);

CREATE TABLE turma_aluno (
    turma_id INT,
    aluno_matricula INT,
    PRIMARY KEY (turma_id, aluno_matricula),
    FOREIGN KEY (turma_id) REFERENCES turma(id),
    FOREIGN KEY (aluno_matricula) REFERENCES aluno(matricula)
);

CREATE TABLE curriculum (
    id INT PRIMARY KEY,
    ano INT,
    semestre INT
);

CREATE TABLE curriculum_disciplina (
    curriculum_id INT,
    disciplina_id INT,
    PRIMARY KEY (curriculum_id, disciplina_id),
    FOREIGN KEY (curriculum_id) REFERENCES curriculum(id),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(_id)
);

CREATE TABLE aluno_disciplina (
    aluno_matricula INT,
    disciplina_id INT,
    PRIMARY KEY (aluno_matricula, disciplina_id),
    FOREIGN KEY (aluno_matricula) REFERENCES aluno(matricula),
    FOREIGN KEY (disciplina_id) REFERENCES disciplina(_id)
);


INSERT INTO professor (matricula, nome, senha) VALUES (1, 'Cristiano', 'senha123');
INSERT INTO professor (matricula, nome, senha) VALUES (2, 'Soraya', 'senha456');

INSERT INTO disciplina (_id, nome, creditos, maxAlunos, minAlunos, tipo_disciplina, professor_id) 
VALUES (1, 'Laboratorio Dev Software', 4, 60, 3, 'OBRIGATORIA', 1);
INSERT INTO disciplina (_id, nome, creditos, maxAlunos, minAlunos, tipo_disciplina, professor_id) 
VALUES (2, 'Introducao a Computacao', 3, 40, 10, 'OPTATIVA', 2);

INSERT INTO turma (id, disciplina_id, professor_id, status, ano, semestre) 
VALUES (1, 1, 1, 'PENDENTE', 2024, 2);
INSERT INTO turma (id, disciplina_id, professor_id, status, ano, semestre) 
VALUES (2, 2, 2, 'PENDENTE', 2024, 2);

INSERT INTO aluno (matricula, nome, senha) VALUES (1, 'Rubens Marcelo', 'aluno123');
INSERT INTO aluno (matricula, nome, senha) VALUES (2, 'Joao Lezsi', 'aluno456');

INSERT INTO turma_aluno (turma_id, aluno_matricula) VALUES (1, 1);
INSERT INTO turma_aluno (turma_id, aluno_matricula) VALUES (2, 2);

INSERT INTO curriculum (id, ano, semestre) VALUES (1, 2024, 2);

INSERT INTO curriculum_disciplina (curriculum_id, disciplina_id) VALUES (1, 1);
INSERT INTO curriculum_disciplina (curriculum_id, disciplina_id) VALUES (1, 2);

INSERT INTO aluno_disciplina (aluno_matricula, disciplina_id) VALUES (1, 1);
INSERT INTO aluno_disciplina (aluno_matricula, disciplina_id) VALUES (2, 2);
