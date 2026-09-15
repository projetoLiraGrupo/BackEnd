INSERT INTO endereco (cep, logradouro, numero, complemento)
VALUES ('01000-000', 'Rua das Flores', '123', 'Apto 42');

INSERT INTO endereco (cep, logradouro, numero, complemento)
VALUES ('01311-200', 'Avenida Paulista', '1500', NULL);

INSERT INTO endereco (cep, logradouro, numero, complemento)
VALUES ('04571-010', 'Avenida Berrini', '500', 'Bloco B');

INSERT INTO aluno (aluno_nome, aluno_email, aluno_senha, aluno_cpf, aluno_possui_responsavel, data_de_nascimento, fk_endereco)
VALUES ('João Silva', 'joao.silva@email.com', 'senhaSegura123', '12345678901', false, '2005-04-15', 1);

INSERT INTO aluno (aluno_nome, aluno_email, aluno_senha, aluno_cpf, aluno_possui_responsavel, data_de_nascimento, fk_endereco)
VALUES ('Maria Oliveira', 'maria.oliveira@email.com', 'maria@2026', '98765432100', true, '2008-11-23', 2);

INSERT INTO aluno (aluno_nome, aluno_email, aluno_senha, aluno_cpf, aluno_possui_responsavel, data_de_nascimento, fk_endereco)
VALUES ('Pedro Santos', 'pedro.santos@email.com', 'p3dr0_senha', '11122233344', false, '2004-01-02', 3);
