# Lira Paulistana — API Back-end

API REST da plataforma Lira Paulistana, desenvolvida com Java e Spring Boot. O projeto reúne operações para cadastro de alunos e endereços, autenticação, gerenciamento de aulas e marcação de aulas pelos alunos.

Este README descreve o código disponível na branch `main` no commit `d6563e6`. Consulte o Swagger e o código-fonte para acompanhar alterações posteriores.

## Sumário

- [Funcionalidades](#funcionalidades)
- [Tecnologias](#tecnologias)
- [Estrutura](#estrutura)
- [Como executar](#como-executar)
- [Configuração](#configuração)
- [Primeiras requisições](#primeiras-requisições)
- [Endpoints](#endpoints)
- [Regras de negócio](#regras-de-negócio)
- [Autenticação e segurança](#autenticação-e-segurança)
- [Respostas e erros](#respostas-e-erros)
- [Testes e cobertura](#testes-e-cobertura)
- [Pontos de atenção](#pontos-de-atenção)

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de alunos e endereços.
- Login de aluno por e-mail e senha, com emissão de JWT.
- Cadastro, consulta e atualização de aulas.
- Marcação e cancelamento de aulas para o aluno autenticado.
- Consulta das próprias aulas e dos alunos marcados em uma aula.
- Validação de dados, tratamento centralizado de exceções e documentação OpenAPI.

**Escopo atual:** existem entidades, repositórios e controladores para professores e instrumentos, mas os respectivos controladores ainda não possuem métodos de endpoint. O login configurado consulta a tabela de alunos; não há login de professor ou administrador implementado nesse fluxo.

## Tecnologias

| Tecnologia | Uso no projeto |
| --- | --- |
| Java 21 | Linguagem e ambiente de execução |
| Spring Boot 4.0.8 e Spring Web MVC | Aplicação e endpoints HTTP |
| Spring Data JPA e Hibernate | Persistência e mapeamento das entidades |
| Spring Security e `java-jwt` 4.5.0 | Autenticação, filtros e tokens JWT |
| Jakarta Bean Validation | Validação das requisições anotadas com `@Valid` |
| H2 e MySQL Connector/J | Banco em memória para desenvolvimento e driver MySQL |
| SpringDoc OpenAPI 3.0.2 | Documentação interativa da API |
| Maven Wrapper | Execução de comandos Maven sem instalação global |
| JUnit, Mockito e JaCoCo | Testes e relatório de cobertura |

O `pom.xml` também declara dependências do AWS SDK para S3 e Lambda; a presença dessas bibliotecas, por si só, não significa que exista uma integração funcional configurada neste código.

## Estrutura

```text
BackEnd/
├── README.md
└── lira_api/
    ├── .mvn/wrapper/
    ├── mvnw
    ├── mvnw.cmd
    ├── pom.xml
    └── src/
        ├── main/
        │   ├── java/com/lira/grupo/api/lira_api/
        │   │   ├── config/security/   # Segurança, filtro e JWT
        │   │   ├── controller/        # Endpoints HTTP
        │   │   ├── entity/            # Entidades JPA
        │   │   ├── exception/         # Exceções e tratamento de erros
        │   │   ├── mapper/            # Conversões entre objetos
        │   │   ├── repository/        # Acesso aos dados
        │   │   ├── requestDto/        # Dados recebidos nas requisições
        │   │   ├── responseDto/       # Dados retornados nas respostas
        │   │   └── service/           # Regras de negócio
        │   └── resources/
        │       ├── application.properties
        │       ├── application-mysql.properties
        │       └── dbLiraGrupo.sql
        └── test/java/                 # Testes da aplicação
```

Em geral, uma requisição passa por **controller → service → repository**. Os DTOs representam os dados de entrada e saída; os mappers convertem os objetos quando necessário.

## Como executar

### Pré-requisitos

- JDK 21 instalado e disponível no terminal.
- Git.
- Acesso aos repositórios Maven na primeira execução, para baixar o Maven e as dependências que ainda não estiverem em cache.
- Opcionalmente, MySQL, se não for utilizar o H2 em memória.

Confira a versão do Java:

```bash
java -version
```

### 1. Clone o repositório

```bash
git clone https://github.com/projetoLiraGrupo/BackEnd.git
cd BackEnd/lira_api
```

### 2. Inicie a aplicação

No Linux ou macOS:

```bash
./mvnw spring-boot:run
```

Se necessário, conceda permissão ao wrapper com `chmod +x mvnw` antes de executá-lo.

No Windows, pelo PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Por padrão, a API utiliza a porta **8080** e um banco H2 em memória. Os dados do H2 são perdidos ao encerrar a aplicação.

| Recurso | Endereço local |
| --- | --- |
| API | `http://localhost:8080` |
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| Especificação OpenAPI | `http://localhost:8080/v3/api-docs` |
| Console H2, quando habilitado | `http://localhost:8080/h2-console` |

No console H2, utilize:

```text
JDBC URL: jdbc:h2:mem:lira;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE
User Name: sa
Password: deixe em branco
```

Esses são os padrões de `application.properties`; se as variáveis de banco forem alteradas, utilize os novos valores no console.

## Configuração

As propriedades estão em `lira_api/src/main/resources/application.properties`. Variáveis de ambiente sobrescrevem os valores indicados abaixo.

| Variável | Padrão | Finalidade |
| --- | --- | --- |
| `SERVER_PORT` | `8080` | Porta HTTP da aplicação. |
| `DB_URL` | `jdbc:h2:mem:lira;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE` | URL JDBC do banco. |
| `DB_USERNAME` | `sa` | Usuário do banco. |
| `DB_PASSWORD` | vazio | Senha do banco. |
| `DB_DRIVER` | `org.h2.Driver` | Classe do driver JDBC. |
| `DDL_AUTO` | `update` | Comportamento de criação/atualização do esquema pelo Hibernate. |
| `SHOW_SQL` | `false` | Exibição de SQL nos logs. |
| `H2_CONSOLE_ENABLED` | `true` | Habilita o console H2. |
| `JWT_SECRET` | valor padrão de desenvolvimento | Segredo para assinatura e validação dos tokens. |
| `JWT_EXPIRATION_SECONDS` | `3600` | Validade do token, em segundos. |
| `COOKIE_SECURE` | `false` | Define o atributo `Secure` do cookie de autenticação. |
| `CORS_ALLOWED_ORIGINS` | `http://localhost:3000,http://localhost:5173` | Origens autorizadas, separadas por vírgulas. |

Por exemplo, para alterar a porta em Linux/macOS:

```bash
SERVER_PORT=8081 ./mvnw spring-boot:run
```

No fish, utilize `env SERVER_PORT=8081 ./mvnw spring-boot:run`.

### Utilizando MySQL

Crie previamente um banco e um usuário com as permissões necessárias. O driver MySQL já está declarado no `pom.xml`.

**Na versão analisada, as configurações em `application-mysql.properties` estão comentadas.** Portanto, ativar apenas o perfil `mysql` não altera a conexão. Uma alternativa é fornecer as variáveis usadas pelo `application.properties`, incluindo `DB_DRIVER`:

```bash
env DB_URL='jdbc:mysql://localhost:3306/lira_paulistana?serverTimezone=America/Sao_Paulo' \
    DB_USERNAME='seu_usuario' \
    DB_PASSWORD='sua_senha' \
    DB_DRIVER='com.mysql.cj.jdbc.Driver' \
    H2_CONSOLE_ENABLED=false \
    ./mvnw spring-boot:run
```

O exemplo usa valores ilustrativos para desenvolvimento. Configure credenciais e parâmetros de conexão apropriados ao ambiente. Antes de utilizar um banco existente, revise o esquema e a configuração `DDL_AUTO`: o padrão `update` permite que o Hibernate altere a estrutura. O arquivo `dbLiraGrupo.sql` está no repositório, mas não é executado automaticamente pela configuração mostrada aqui; confira sua compatibilidade com as entidades antes de aplicá-lo.

## Primeiras requisições

Os exemplos abaixo usam `curl`, com a API local na porta 8080. Utilize dados fictícios. Os identificadores são exemplos: substitua-os pelos IDs retornados na sua execução.

### 1. Cadastre um endereço

`POST /enderecos` é público e retorna o endereço cadastrado, incluindo `idEndereco`:

```bash
curl -i -X POST 'http://localhost:8080/enderecos' \
  -H 'Content-Type: application/json' \
  -d '{
    "logradouro": "Rua Exemplo",
    "numero": "100",
    "complemento": "Sala 2",
    "cep": "01001-000"
  }'
```

Anote o valor de `idEndereco` recebido na resposta.

### 2. Cadastre um aluno

Em `fkEndereco`, informe o ID do endereço criado no passo anterior. Neste exemplo, suponha que seja `1`:

```bash
curl -i -X POST 'http://localhost:8080/alunos/cadastrar' \
  -H 'Content-Type: application/json' \
  -d '{
    "alunoNome": "Aluno Exemplo",
    "alunoEmail": "aluno.exemplo@lira.test",
    "alunoSenha": "senha123",
    "alunoCpf": "123.456.789-00",
    "dataDeNascimento": "2000-01-01",
    "alunoPossuiResponsavel": false,
    "fkEndereco": 1
  }'
```

O CPF acima é fictício e usado apenas para ilustrar o formato aceito. Cada aluno precisa ter e-mail e CPF ainda não cadastrados.

### 3. Faça login

```bash
curl -i -c cookies.txt -X POST 'http://localhost:8080/auth/login' \
  -H 'Content-Type: application/json' \
  -d '{
    "email": "aluno.exemplo@lira.test",
    "senha": "senha123"
  }'
```

A resposta contém os dados do aluno e o campo `token`. O servidor também envia o JWT em um cookie `authToken`; o parâmetro `-c` salva o cookie localmente para o próximo exemplo. Trate tanto o token quanto o arquivo de cookies como credenciais.

### 4. Consulte suas aulas

```bash
curl -i -b cookies.txt 'http://localhost:8080/alunos/me/aulas'
```

Também é possível enviar o campo `token` retornado pelo login no cabeçalho `Authorization`:

```bash
curl -i 'http://localhost:8080/alunos/me/aulas' \
  -H 'Authorization: Bearer COLE_O_TOKEN_AQUI'
```

Para apagar o cookie no cliente, chame `POST /auth/logout` e remova o arquivo local de cookies usado no exemplo. O logout não revoga tokens JWT já emitidos no servidor; eles continuam válidos até a expiração.

## Endpoints

A tabela descreve os métodos declarados nos controladores e a política de acesso configurada. **Autenticado** significa que a requisição precisa de um token de aluno aceito pela API.

### Autenticação

| Método | Rota | Acesso | Operação |
| --- | --- | --- | --- |
| `POST` | `/auth/login` | Público | Autentica um aluno e emite JWT. |
| `POST` | `/auth/logout` | Público | Solicita a remoção do cookie `authToken`. |

### Alunos e endereços

| Método | Rota | Acesso | Operação |
| --- | --- | --- | --- |
| `POST` | `/alunos/cadastrar` | Público | Cadastra aluno. |
| `GET` | `/alunos` | Autenticado | Lista alunos. |
| `GET` | `/alunos/{id}` | Autenticado | Busca aluno pelo ID. |
| `PUT` | `/alunos/{id}` | Autenticado | Atualiza os dados completos de um aluno. |
| `PATCH` | `/alunos/{id}` | Autenticado | Atualiza campos informados de um aluno. |
| `DELETE` | `/alunos/{id}` | Autenticado | Remove aluno. |
| `POST` | `/enderecos` | Público | Cadastra endereço. |
| `GET` | `/enderecos` | Autenticado | Lista endereços. |
| `GET` | `/enderecos/{id}` | Autenticado | Busca endereço pelo ID. |
| `PUT` | `/enderecos/{id}` | Autenticado | Atualiza endereço. |
| `DELETE` | `/enderecos/{id}` | Autenticado | Remove endereço. |

### Aulas e marcações

| Método | Rota | Acesso | Operação |
| --- | --- | --- | --- |
| `GET` | `/aulas` | Autenticado | Lista aulas. |
| `GET` | `/aulas/{id}` | Autenticado | Busca aula pelo ID. |
| `POST` | `/aulas` | Autenticado | Cadastra aula. |
| `PUT` | `/aulas/{id}` | Autenticado | Atualiza aula. |
| `POST` | `/aulas/{idAula}/marcacoes` | Autenticado | Marca a aula para o aluno do token; não exige corpo. |
| `DELETE` | `/aulas/{idAula}/marcacoes` | Autenticado | Cancela a marcação do aluno do token. |
| `GET` | `/alunos/me/aulas` | Autenticado | Lista as marcações do aluno do token. |
| `GET` | `/aulas/{idAula}/alunos` | Autenticado | Lista as marcações da aula. |

Para cadastrar ou atualizar uma aula, informe `diaHora`, `nivel`, `fkEndereco` e `fkProfessor`. O endereço e o professor devem existir previamente. Como ainda não há endpoint para cadastrar professores, os dados de professor precisam estar disponíveis no banco por outro processo apropriado ao ambiente.

Exemplo de corpo para `POST /aulas` ou `PUT /aulas/{id}`:

```json
{
  "diaHora": "2030-12-10T15:30:00",
  "nivel": "Iniciante",
  "fkEndereco": 1,
  "fkProfessor": 1
}
```

Substitua os IDs por registros existentes e escolha uma data e hora futuras. A data e hora não incluem fuso horário.

Com uma aula existente, o aluno autenticado pode marcá-la sem enviar corpo na requisição:

```bash
curl -i -b cookies.txt -X POST 'http://localhost:8080/aulas/1/marcacoes'
```

## Regras de negócio

### Alunos e endereços

- O cadastro de aluno exige nome, e-mail, senha, CPF, data de nascimento, indicação de responsável e ID de um endereço existente.
- E-mails são normalizados para letras minúsculas; CPFs são armazenados somente com dígitos. E-mail e CPF duplicados são tratados como conflito.
- O DTO de cadastro aceita CPF com 11 dígitos ou no formato `000.000.000-00`. A regra atual verifica **formato**, não o cálculo dos dígitos verificadores.
- A data de nascimento deve estar no passado. A senha enviada no cadastro ou na atualização completa deve ter entre 6 e 72 caracteres; ela é armazenada com BCrypt.
- `alunoPossuiResponsavel` é um campo booleano obrigatório no cadastro. Não presuma, a partir desse campo isolado, que o fluxo completo de cadastro e vinculação de responsáveis esteja disponível por API.
- Para endereços, `logradouro`, `numero` e `cep` são obrigatórios; o CEP aceita oito dígitos, com ou sem hífen.
- O `PUT /alunos/{id}` usa validação completa do DTO. No `PATCH`, o controlador não aplica `@Valid`: os campos enviados não passam pelas mesmas validações automáticas. Considere essa limitação ao integrar a API.

### Aulas

- Para criar ou atualizar uma aula, a data e hora devem estar no futuro e o professor e o endereço devem existir.
- Um professor não pode ter duas aulas com a mesma data e hora.
- O aluno não pode marcar a mesma aula duas vezes, marcar aulas com a mesma data e hora, nem marcar uma aula sem vagas ou que já passou.
- Uma marcação de aula que já passou não pode ser cancelada.
- Na ausência de uma configuração de `AulaGrupo` para a aula, a capacidade considerada pelo serviço é de uma vaga. Quando existe configuração de grupo, o serviço utiliza a quantidade máxima de alunos registrada.

As verificações de conflito de horário comparam a data e a hora cadastradas; não há uma duração de aula considerada nessas comparações.

## Autenticação e segurança

O `POST /auth/login` recebe `email` e `senha`. Após a autenticação, o backend retorna um JWT no corpo da resposta e no cookie `authToken`.

- A senha do aluno é verificada com BCrypt.
- O JWT usa HMAC256, emissor `lira-api` e validade configurável.
- Nas rotas protegidas, o filtro aceita `Authorization: Bearer <token>` ou o cookie `authToken`. Se um cabeçalho Bearer estiver presente, ele tem prioridade.
- O cookie é `HttpOnly`, tem `SameSite=Strict` e usa `Secure` conforme `COOKIE_SECURE`.
- A API está configurada como stateless; o logout limpa o cookie, mas não mantém uma lista de revogação de tokens.
- O CORS usa as origens configuradas em `CORS_ALLOWED_ORIGINS` e permite credenciais. Para enviar cookies em chamadas do navegador entre origens, o cliente também precisa habilitar o envio de credenciais. `SameSite=Strict` pode impedir o envio em contextos entre sites diferentes, mesmo com CORS configurado.

**Limitação de autorização:** as rotas protegidas verificam se há autenticação, mas a configuração atual não diferencia permissões por papel nem garante que um aluno acesse ou altere apenas os próprios dados. Por exemplo, endpoints de consulta e alteração por ID ficam disponíveis para um aluno autenticado. Revise as regras de autorização antes de disponibilizar a API a usuários reais.

**Para ambientes públicos:** defina um `JWT_SECRET` longo, aleatório e exclusivo; use HTTPS e `COOKIE_SECURE=true`; restrinja as origens CORS; desative o console H2; e revise CSRF, permissões, persistência e estratégia de migração. A configuração atual desabilita CSRF e habilita o console H2 por padrão, portanto exige revisão antes de produção.

## Respostas e erros

As operações de criação de aluno, endereço, aula e marcação retornam `201` quando concluídas. As operações de exclusão de aluno, endereço e marcação retornam `204` sem corpo; consultas e atualizações bem-sucedidas retornam `200`.

O `GlobalExceptionHandler` trata erros da aplicação com uma estrutura semelhante a:

```json
{
  "timestamp": "2026-11-25T14:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Dados da requisição inválidos.",
  "path": "/alunos/cadastrar",
  "details": ["alunoNome: O nome do aluno é obrigatório."]
}
```

| Código | Exemplo de situação tratada |
| --- | --- |
| `400` | Um campo de uma requisição validada não atende às regras. |
| `401` | Credenciais inválidas no login. |
| `404` | Aluno, endereço, aula ou outro recurso necessário não encontrado. |
| `409` | E-mail ou CPF duplicado, conflito de horário, falta de vagas ou violação de integridade. |
| `422` | Uma operação válida viola uma regra de negócio, como marcar aula que já passou. |
| `500` | Erro interno não previsto. |

Erros gerados diretamente pelos filtros do Spring Security podem ter status ou formato de resposta diferente do `ApiError` acima.

## Testes e cobertura

Na pasta `lira_api`, execute:

```bash
./mvnw test
```

No Windows:

```powershell
.\mvnw.cmd test
```

Os testes existentes cobrem, entre outros cenários, conflitos de e-mail e CPF, criação e atualização de aulas, marcação e cancelamento de aulas e autenticação de aluno. O `LoginTest` utiliza o contexto Spring e `MockMvc`; os testes de serviços utilizam mocks.

O JaCoCo está configurado no `pom.xml` para gerar um relatório durante a fase de testes. Após uma execução bem-sucedida, abra:

```text
lira_api/target/site/jacoco/index.html
```

Para gerar o pacote da aplicação:

```bash
./mvnw clean package
```

O comando também executa os testes por padrão e grava o resultado em `lira_api/target/`.

## Pontos de atenção

- O H2 padrão é temporário: ao reiniciar a aplicação, é necessário cadastrar os dados novamente.
- O perfil MySQL do repositório está comentado; configure a conexão conforme a seção [Utilizando MySQL](#utilizando-mysql).
- Não existem métodos de endpoint nos controladores de professor e instrumento nesta versão, nem autenticação desses perfis no fluxo atual.
- A criação de aulas depende de professor já existente. O projeto também não expõe, nesta versão, um endpoint de exclusão de aula.
- Validação de CPF, validação de atualização parcial, autorização por perfil e por proprietário, gestão de responsáveis e proteção de produção são pontos a evoluir.
- Antes de integrar uma interface, confira as rotas e os esquemas gerados no Swagger. Ter uma entidade ou dependência declarada não garante que a funcionalidade correspondente esteja exposta pela API.
