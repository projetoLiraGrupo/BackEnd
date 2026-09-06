CREATE DATABASE IF NOT EXISTS lira_paulistana
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE lira_paulistana;

CREATE TABLE IF NOT EXISTS Endereco (
    idEndereco INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(45) NOT NULL,
    numero VARCHAR(5),
    cep CHAR(8)
);

CREATE TABLE IF NOT EXISTS Admin (
    idAdmin INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Professor (
    idProfessor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL,
    telefone CHAR(11),
    criadoEm DATE,
    fkEndereco INT,
    FOREIGN KEY (fkEndereco) REFERENCES Endereco(idEndereco)
);

CREATE TABLE IF NOT EXISTS Aluno (
    idAluno INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    dataNascimento DATE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL,
    Endereco_idEndereco INT,
    FOREIGN KEY (Endereco_idEndereco) REFERENCES Endereco(idEndereco)
);

CREATE TABLE IF NOT EXISTS Responsavel (
    idResponsavel INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    telefone CHAR(11),
    Endereco_idEndereco INT,
    FOREIGN KEY (Endereco_idEndereco) REFERENCES Endereco(idEndereco)
);

CREATE TABLE IF NOT EXISTS Instrumento (
    idInstrumento INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    descricao TEXT
);

CREATE TABLE IF NOT EXISTS Preferencia (
    idPreferencia INT AUTO_INCREMENT PRIMARY KEY,
    categoria VARCHAR(45),
    valor INT,
    aplicavelPara VARCHAR(45),
    tipo VARCHAR(45)
);

CREATE TABLE IF NOT EXISTS Experiencia (
    idExperiencia INT AUTO_INCREMENT PRIMARY KEY,
    experiencia VARCHAR(45),
    inicioEm DATE,
    terminoEm DATE,
    Professor_idProfessor INT,
    FOREIGN KEY (Professor_idProfessor)
        REFERENCES Professor(idProfessor)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Formacao (
    idFormacao INT AUTO_INCREMENT PRIMARY KEY,
    curso VARCHAR(45),
    instituicao VARCHAR(45),
    grau VARCHAR(45),
    status VARCHAR(45),
    dtTermino DATE,
    fkProfessor INT,
    FOREIGN KEY (fkProfessor)
        REFERENCES Professor(idProfessor)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Aula (
    idAula INT AUTO_INCREMENT PRIMARY KEY,
    diaHora DATETIME,
    nivel VARCHAR(45),
    Endereco_idEndereco INT,
    Professor_idProfessor INT,
    Aluno_idAluno INT,
    qtdAlunos INT,
    FOREIGN KEY (Endereco_idEndereco) REFERENCES Endereco(idEndereco),
    FOREIGN KEY (Professor_idProfessor) REFERENCES Professor(idProfessor),
    FOREIGN KEY (Aluno_idAluno) REFERENCES Aluno(idAluno)
);

CREATE TABLE IF NOT EXISTS AulaGrupo (
    idAulaGrupo INT AUTO_INCREMENT PRIMARY KEY,
    Aula_idAula INT,
    Admin_idAdmin INT,
    inicioEm DATETIME,
    terminoEm DATETIME,
    qtdAlunosMax INT,
    FOREIGN KEY (Aula_idAula)
        REFERENCES Aula(idAula)
        ON DELETE CASCADE,
    FOREIGN KEY (Admin_idAdmin)
        REFERENCES Admin(idAdmin)
);

CREATE TABLE IF NOT EXISTS Suporte (
    idSuporte INT AUTO_INCREMENT PRIMARY KEY,
    suportecol TINYINT(2),
    nivel VARCHAR(45),
    descricao VARCHAR(500),
    tipo VARCHAR(45),
    fkAluno INT,
    FOREIGN KEY (fkAluno)
        REFERENCES Aluno(idAluno)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Aluno_has_Responsavel (
    idResponsavelAluno INT AUTO_INCREMENT PRIMARY KEY,
    fkAluno INT NOT NULL,
    fkResponsavel INT NOT NULL,
    UNIQUE (fkAluno, fkResponsavel),
    FOREIGN KEY (fkAluno)
        REFERENCES Aluno(idAluno)
        ON DELETE CASCADE,
    FOREIGN KEY (fkResponsavel)
        REFERENCES Responsavel(idResponsavel)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS InstrumentoAluno (
    Instrumento_idInstrumento INT NOT NULL,
    Aluno_idAluno INT NOT NULL,
    nivel VARCHAR(45),
    PRIMARY KEY (Instrumento_idInstrumento, Aluno_idAluno),
    FOREIGN KEY (Instrumento_idInstrumento)
        REFERENCES Instrumento(idInstrumento)
        ON DELETE CASCADE,
    FOREIGN KEY (Aluno_idAluno)
        REFERENCES Aluno(idAluno)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS InstrumentoProfessor (
    fkProfessor INT NOT NULL,
    fkInstrumento INT NOT NULL,
    PRIMARY KEY (fkProfessor, fkInstrumento),
    FOREIGN KEY (fkProfessor)
        REFERENCES Professor(idProfessor)
        ON DELETE CASCADE,
    FOREIGN KEY (fkInstrumento)
        REFERENCES Instrumento(idInstrumento)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PreferenciaAluno (
    fkAluno INT NOT NULL,
    fkPreferencia INT NOT NULL,
    PRIMARY KEY (fkAluno, fkPreferencia),
    FOREIGN KEY (fkAluno)
        REFERENCES Aluno(idAluno)
        ON DELETE CASCADE,
    FOREIGN KEY (fkPreferencia)
        REFERENCES Preferencia(idPreferencia)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS PreferenciaProfessor (
    fkProfessor INT NOT NULL,
    fkPreferencia INT NOT NULL,
    PRIMARY KEY (fkProfessor, fkPreferencia),
    FOREIGN KEY (fkProfessor)
        REFERENCES Professor(idProfessor)
        ON DELETE CASCADE,
    FOREIGN KEY (fkPreferencia)
        REFERENCES Preferencia(idPreferencia)
        ON DELETE CASCADE
);
