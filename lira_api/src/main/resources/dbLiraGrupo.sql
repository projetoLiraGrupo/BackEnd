CREATE DATABASE IF NOT EXISTS dbLiraGrupo;
USE dbLiraGrupo;

/*
	Admin
    endereco
        Aluno
			suporte
            responsavelAluno
		 - Responsavel
		Professor
			Experiencia
            Formação
    instrumento
		InstrumentoProfessor
        InstrumentoAluno
	Preferencia
		PreferenciaProfessor
        PreferenciaAluno

*/

-- TODOS --
CREATE TABLE IF NOT EXISTS endereco(
idEndereco INT PRIMARY KEY AUTO_INCREMENT,
cep CHAR(8) NOT NULL, 
estado VARCHAR(50) NOT NULL,
cidade VARCHAR(50) NOT NULL,
bairro VARCHAR(50) NOT NULL,
rua VARCHAR(50) NOT NULL,
numero VARCHAR(5) NOT NULL,
complemento VARCHAR(50)
);

-- ADMIN 
CREATE TABLE IF NOT EXISTS adm(
idAdmin INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR (100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
SENHA VARCHAR(50) NOT NULL,
fkEndereco INT,
	CONSTRAINT fkEnderecoAdm
		FOREIGN KEY (fkEndereco) REFERENCES endereco(idEndereco)
);


-- ALUNO -- 
CREATE TABLE IF NOT EXISTS aluno(
idAluno INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
senha VARCHAR(50) NOT NULL,
dataNascimento DATE NOT NULL,
telefone CHAR(11),
fkEndereco INT,
	CONSTRAINT fkEnderecoALuno
		FOREIGN KEY (fkEndereco) REFERENCES endereco(idEndereco)
);

CREATE TABLE IF NOT EXISTS responsavel(
idResponsavel INT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
telefone CHAR(11) NOT NULL,
fkEndereco INT,
	CONSTRAINT fkEnderecoResponsavel
		FOREIGN KEY (fkEndereco) REFERENCES endereco(idEndereco)
);

CREATE TABLE IF NOT EXISTS responsavelAluno(
fkResponsavel INT,
fkAluno INT,
	CONSTRAINT pkResponsavelAluno
		PRIMARY KEY (fkResponsavel, fkAluno),
	CONSTRAINT fkResponsavelResponsavelAluno
		FOREIGN KEY (fkResponsavel) REFERENCES responsavel(idResponsavel)
    ,
    CONSTRAINT fkAlunoResponsavelAluno
		FOREIGN KEY (fkAluno) REFERENCES aluno(idAluno)
);

CREATE TABLE IF NOT EXISTS suporte(
idSuporte INT PRIMARY KEY AUTO_INCREMENT,
suporte TINYINT(1) NOT NULL DEFAULT 0,
	CONSTRAINT necessitaSuporte CHECK (suporte in (0, 1)), 
nivel VARCHAR(45),
descriacao VARCHAR(45),
tipo VARCHAR(45),
fkAluno INT, 
	CONSTRAINT fkAlunoSuporte 
		FOREIGN KEY (fkAluno) REFERENCES aluno(idAluno)
);

-- PROFESSOR --
CREATE TABLE IF NOT EXISTS professor(
idProfessor INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
senha VARCHAR(50) NOT NULL,
telefone CHAR(8) NOT NULL,
criadoEm DATE DEFAULT (CURRENT_DATE),
fkEndereco INT,
	CONSTRAINT fkEnderecoProfessor
		FOREIGN KEY (fkEndereco) REFERENCES endereco(idEndereco)
);

CREATE TABLE IF NOT EXISTS experiencia(
idExperiencia INT PRIMARY KEY AUTO_INCREMENT,
experiencia VARCHAR(45) NOT NULL,
inicioEm DATE NOT NULL,
terminoEm DATE,
fkProfessor INT,
	CONSTRAINT fkProfessorExperiencia
		FOREIGN KEY (fkProfessor) REFERENCES professor(idProfessor)
);

CREATE TABLE IF NOT EXISTS formacao (
idFormação INT PRIMARY KEY AUTO_INCREMENT,
curso VARCHAR(45) NOT NULL,
instituicao VARCHAR(45) NOT NULL,
grau VARCHAR(45) NOT NULL,
fkProfessor INT, 
	CONSTRAINT fkProfessorFormacao
		FOREIGN KEY (fkProfessor) REFERENCES professor(idProfessor)
);

-- INTRUMENTO --
CREATE TABLE IF NOT EXISTS instrumento(
idInstrumento INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR (45) NOT NULL,
descricao TEXT(300)
);
CREATE TABLE IF NOT EXISTS instrumentoProfessor(
idInstrumentoProfessor INT AUTO_INCREMENT,
fkInstrumento INT,
fkProfessor INT,
	CONSTRAINT pkInstrumentoProfessor
		PRIMARY KEY (idInstrumentoProfessor, fkInstrumento, fkProfessor),
	CONSTRAINT fkIntrumentoInstrumentoProfessor
		FOREIGN KEY (fkInstrumento) REFERENCES instrumento(idInstrumento),
	CONSTRAINT fkProfessorInstrumentoProfessor
		FOREIGN KEY (fkProfessor) REFERENCES professor(idProfessor)
);

CREATE TABLE IF NOT EXISTS instrumentoAluno(
idInstrumentoAluno INT AUTO_INCREMENT,
fkInstrumento INT, 
fkAluno INT,
	CONSTRAINT pkInstrumentoAluno
		PRIMARY KEY (idInstrumentoALuno, fkInstrumento, fkAluno),
	CONSTRAINT fkIntrumentoInstrumentoAluno 
		FOREIGN KEY (fkInstrumento) REFERENCES instrumento(idInstrumento),
	CONSTRAINT fkAlunoInstrumentoAluno
		FOREIGN KEY (fkAluno) REFERENCES aluno(idAluno),
nivel VARCHAR(45),
	CONSTRAINT nivelInstrumentoALuno
		CHECK (nivel IN ("Iniciante", "Intermediario", "Avançado"))
);


-- PREFERENCIAS --
CREATE TABLE IF NOT EXISTS preferencias(
idPreferencia INT PRIMARY KEY AUTO_INCREMENT,
categoria VARCHAR(45),
valor INT,
aplicavelPara VARCHAR(9),
	CONSTRAINT aplicavelParaPreferencias
		CHECK (aplicavelPara IN ('Aluno', 'Professor')),
tipo VARCHAR(11),
	CONSTRAINT tipoPreferencia
		CHECK (tipo IN ('Preferencia', 'Restrição'))
);

CREATE TABLE IF NOT EXISTS preferenciasProfessor(
idPreferenciaProfessor INT AUTO_INCREMENT, 
fkPreferencia INT,
fkProfessor INT,
	CONSTRAINT pkPreferenciaProfessor
		PRIMARY KEY (idPreferenciaProfessor, fkPreferencia, fkProfessor),
	CONSTRAINT fkPreferenciaPreferenciaProfessor
		FOREIGN KEY (fkPreferencia) REFERENCES preferencias(idPreferencia),
	CONSTRAINT fkProfessorPreferenciaProfessor
		FOREIGN KEY (fkProfessor) REFERENCES professor(idProfessor)
);

CREATE TABLE IF NOT EXISTS preferenciasAluno(
idPreferenciaAluno INT AUTO_INCREMENT,
fkPreferencia INT,
fkAluno INT,
	CONSTRAINT pkPreferenciaAluno
		PRIMARY KEY (idPreferenciaAluno, fkPreferencia, fkAluno),
	CONSTRAINT fkPreferenciaPreferenciaAluno
		FOREIGN KEY (fkPreferencia) REFERENCES preferencias(idPreferencia),
	CONSTRAINT fkAlunoPreferenciaAluno
		FOREIGN KEY (fkAluno) REFERENCES aluno(idAluno)
);

-- AULA --
CREATE TABLE IF NOT EXISTS aula(
idAula INT PRIMARY KEY AUTO_INCREMENT,
diaHora DATETIME,
nivel VARCHAR(45),
	CONSTRAINT nivelInstrumentoAula
		CHECK (nivel IN ("Iniciante", "Intermediario", "Avançado")),
fkEndereco INT,
	CONSTRAINT fkEnderecoAula
		FOREIGN KEY (fkEndereco) REFERENCES endereco(idEndereco),
fkProfessor INT,
	CONSTRAINT fkProfessorAula
		FOREIGN KEY (fkProfessor) REFERENCES professor(idProfessor)
);

CREATE TABLE IF NOT EXISTS alunoAula(
	fkAluno INT NOT NULL,
    fkAula INT NOT NULL,
    CONSTRAINT alunoAlunoAula
		FOREIGN KEY (fkAluno) REFERENCES aluno(idAluno),
	CONSTRAINT aulaAlunoAula
		FOREIGN KEY (fkAula) REFERENCES aula(idAula)
);

CREATE TABLE IF NOT EXISTS aulaGrupo(
idAulaGrupo INT PRIMARY KEY AUTO_INCREMENT,
fkAula INT,
	CONSTRAINT fkAulaAulaGrupo 
		FOREIGN KEY (fkAula) REFERENCES aula(idAula),
fkAdmin INT,
	CONSTRAINT fkAdminAulaGrupo
		FOREIGN KEY (fkAdmin) REFERENCES adm(idAdmin),
inicioEm DATE NOT NULL,
terminoEm DATE NOT NULL,
qtdAlunosMax INT NOT NULL
);

/*
-- select all --
SELECT idAdmin, nome, email FROM adm;
SELECT idAluno, nome, dataNascimento, email, fkEndereco FROM aluno;
SELECT idProfessor, nome, email, telefone, criadoEm, fkEndereco FROM Professor;
SELECT * FROM endereco;
SELECT * FROM instrumento;
SELECT * FROM instrumentoProfessor;
SELECT * FROM instrumentoAluno;
SELECT * FROM Preferencias;
SELECT * FROM preferenciasProfessor;
SELECT * FROM preferenciasAluno;
SELECT * FROM formacao;
SELECT * FROM experiencia;
SELECT * FROM aula;
SELECT * FROM aulaGrupo;
SELECT * FROM suporte;
SELECT * FROM responsavel;
SELECT * FROM responsavelAluno;
*/

/*
-- select login -- 
SELECT idAdmin, nome, email FROM adm WHERE email = "" AND senha = "";
SELECT idAluno, nome, dataNascimento, email, fkEndereco FROM aluno WHERE email = "" AND senha = "";
SELECT idProfessor, nome, email, telefone, criadoEm, fkEndereco FROM Professor WHERE email = "" AND senha = "";
*/

/*
-- select join --
SELECT a.idAluno, a.nome AS nomeAluno, a.email AS emailAluno, a.dataNascimento AS dataNascAluno, 
	COALESCE(a.telefone, 'Não Cadastrado' ) AS telefoneAluno, a.fkEndereco AS enderecoAluno, 
    resp.idResponsavel, resp.nome AS nomeResp, resp.telefone AS telefoneResp, resp.fkEndereco AS enderecoResp
    FROM aluno a JOIN responsavelAluno ON idAluno = fkAluno
	JOIN responsavel resp ON fkResponsavel = idResponsavel;

*/

-- DROP DATABASE dbLiraGrupo;