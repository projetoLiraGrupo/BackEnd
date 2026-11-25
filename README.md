# Lira API — Backend

Backend da plataforma **Lira Paulistana**, desenvolvido como uma API REST com Java e Spring Boot.

O projeto centraliza regras de negócio, persistência de dados, autenticação e integrações utilizadas pela aplicação, mantendo o frontend desacoplado do backend.

## Sumário

- [Tecnologias](#tecnologias)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Clonando o projeto](#clonando-o-projeto)
- [Executando](#executando)
- [Banco de dados](#banco-de-dados)
- [Swagger / OpenAPI](#swagger--openapi)
- [Autenticação e segurança](#autenticação-e-segurança)
- [Endpoints de aluno](#endpoints-de-aluno)
- [Validações](#validações)
- [Senhas](#senhas)
- [JWT](#jwt)
- [AWS](#aws)
- [Testes](#testes)
- [Build](#build)

## Tecnologias

- Java 21
- Spring Boot 4.0.8
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT (`java-jwt` 4.5.0)
- Bean Validation
- Maven
- H2 Database para desenvolvimento
- MySQL Connector/J
- SpringDoc OpenAPI 3.0.2
- AWS SDK para S3 e Lambda
- JUnit 6
- Mockito

## Estrutura do projeto

```text
BackEnd/
├── lira_api/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/lira/grupo/api/lira_api/
│   │   │   │   ├── aws/
│   │   │   │   ├── config/
│   │   │   │   │   └── security/
│   │   │   │   ├── controller/
│   │   │   │   │   └── aws/
│   │   │   │   ├── entity/
│   │   │   │   │   └── dto/
│   │   │   │   │       └── response/
│   │   │   │   ├── exception/
│   │   │   │   ├── mapper/
│   │   │   │   ├── repository/
│   │   │   │   └── service/
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── application-mysql.properties
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
└── README.md
```

A aplicação segue uma organização em camadas:

```text
HTTP Request
     ↓
Controller
     ↓
Service
     ↓
Repository
     ↓
Database
```

Os DTOs controlam os dados recebidos e retornados pela API, enquanto os mappers realizam a conversão entre DTOs e entidades.

## Pré-requisitos

Para executar o projeto localmente:

- Java JDK 21
- Git

Não é obrigatório instalar o Maven globalmente, pois o projeto possui **Maven Wrapper**.

Verifique o Java:

```bash
java --version
```

## Clonando o projeto

```bash
git clone https://github.com/projetoLiraGrupo/BackEnd.git
cd BackEnd/lira_api
```

## Executando

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Caso o wrapper ainda não tenha permissão de execução:

```bash
chmod +x mvnw
./mvnw spring-boot:run
```

### Windows

```powershell
mvnw.cmd spring-boot:run
```

Por padrão, a aplicação ficará disponível em:

```text
http://localhost:8080
```

A porta pode ser alterada pela variável `SERVER_PORT`:

```bash
SERVER_PORT=8081 ./mvnw spring-boot:run
```

## Banco de dados

Por padrão, o ambiente local utiliza um banco H2 em memória:

```properties
spring.datasource.url=jdbc:h2:mem:lira;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
```

O comportamento de criação e atualização do esquema é configurado por:

```properties
spring.jpa.hibernate.ddl-auto=update
```

> O H2 é adequado para desenvolvimento e testes. Dados em memória são perdidos ao reiniciar a aplicação.

### H2 Console

Com a aplicação em execução:

```text
http://localhost:8080/h2-console
```

Configuração padrão:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:lira;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE` |
| User Name | `sa` |
| Password | vazio |

O console pode ser desativado com:

```properties
H2_CONSOLE_ENABLED=false
```

### MySQL

A aplicação possui um arquivo de configuração opcional para MySQL em `src/main/resources/application-mysql.properties`.

Antes de habilitar o perfil, configure o banco e as variáveis de ambiente:

```bash
export DB_URL="jdbc:mysql://localhost:3306/lira_paulistana?useSSL=false&serverTimezone=America/Sao_Paulo&allowPublicKeyRetrieval=true"
export DB_USERNAME="root"
export DB_PASSWORD="sua-senha"
```

Linux/macOS:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

Windows:

```powershell
mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

## Swagger / OpenAPI

A documentação interativa pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

A especificação OpenAPI também pode ser consultada em:

```text
http://localhost:8080/v3/api-docs
```

## Autenticação e segurança

A API utiliza **Spring Security** e autenticação baseada em JWT.

Fluxo esperado:

```text
Cliente
   ↓
POST /auth/login
   ↓
Validação das credenciais
   ↓
JWT
   ↓
Authorization: Bearer <token>
   ↓
Rotas protegidas
```

O token deve ser enviado no header:

```http
Authorization: Bearer SEU_TOKEN_JWT
```

A política de acesso deve ser validada em `SecurityConfigurations` antes de publicar a aplicação em produção. Evite manter liberações temporárias destinadas apenas a testes.

Origens CORS podem ser configuradas por variável de ambiente:

```bash
export CORS_ALLOWED_ORIGINS="http://localhost:3000,http://localhost:5173"
```

Em produção, substitua os valores locais pelos domínios reais das aplicações cliente.

## Endpoints de aluno

Base:

```text
/alunos
```

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/alunos/cadastrar` | Cadastra um aluno |
| `GET` | `/alunos` | Lista todos os alunos |
| `GET` | `/alunos/{id}` | Busca um aluno pelo ID |
| `PUT` | `/alunos/{id}` | Atualiza os dados de um aluno |
| `PATCH` | `/alunos/{id}` | Atualiza parcialmente um aluno |
| `DELETE` | `/alunos/{id}` | Remove um aluno |

### Exemplo de cadastro

```json
{
  "alunoNome": "Aluno Exemplo",
  "alunoEmail": "aluno@email.com",
  "alunoSenha": "senha123",
  "alunoCpf": "123.456.789-00",
  "dataDeNascimento": "2005-01-01",
  "alunoPossuiResponsavel": false,
  "fkEndereco": 1
}
```

Datas devem ser informadas no formato ISO `AAAA-MM-DD`. Valide o formato de CPF aceito pela implementação dos DTOs antes de integrar o frontend.

## Validações

Os DTOs utilizam Jakarta Bean Validation para validar as informações recebidas pela API.

As regras podem incluir:

- campos obrigatórios;
- tamanho mínimo e máximo;
- e-mail válido;
- formato de CPF;
- datas válidas;
- relacionamentos existentes;
- prevenção de e-mail ou CPF duplicado.

Erros relacionados a recursos inexistentes, validações ou conflitos de cadastro são tratados pela camada de aplicação.

## Senhas

As senhas não devem ser armazenadas em texto puro.

A aplicação utiliza `PasswordEncoder` com BCrypt para gerar o hash antes da persistência.

```text
senha recebida
     ↓
BCrypt
     ↓
hash
     ↓
banco de dados
```

## JWT

O serviço de token utiliza HMAC256.

Em produção, configure uma chave própria e segura:

```bash
export JWT_SECRET="uma-chave-longa-aleatoria-e-privada"
export JWT_EXPIRATION_SECONDS="3600"
export COOKIE_SECURE="true"
```

Variáveis disponíveis:

| Variável | Propriedade | Padrão |
|---|---|---|
| `JWT_SECRET` | `api.security.token.secret` | fallback exclusivo para desenvolvimento |
| `JWT_EXPIRATION_SECONDS` | `api.security.token.expiration-seconds` | `3600` |
| `COOKIE_SECURE` | `api.security.cookie.secure` | `false` |

> Nunca utilize o segredo padrão, senhas reais ou tokens em produção ou diretamente no código-fonte e no Git.

## AWS

A integração com AWS é opcional durante o desenvolvimento. Quando utilizada, o AWS SDK pode obter credenciais pela cadeia padrão:

- variável de ambiente;
- perfil configurado em `~/.aws`;
- credenciais de uma instância ou função IAM;
- outras fontes suportadas pelo SDK.

Exemplo opcional com variáveis de ambiente:

```bash
export AWS_REGION="us-east-1"
export AWS_S3_BUCKET="nome-do-bucket"
export AWS_ACCESS_KEY_ID="sua-access-key"
export AWS_SECRET_ACCESS_KEY="sua-secret-key"
```

Para ambientes hospedados na AWS, prefira IAM Role, workload identity ou outra identidade gerenciada em vez de chaves estáticas.

```properties
aws.s3.region=${AWS_REGION:us-east-1}
aws.s3.bucket-name=${AWS_S3_BUCKET:}
```

## Testes

Linux/macOS:

```bash
./mvnw test
```

Windows:

```powershell
mvnw.cmd test
```

Os testes usam o Spring Boot Test, JUnit, Mockito e Spring Security Test conforme declarado no `pom.xml`.

## Build

Linux/macOS:

```bash
./mvnw clean package
```

Windows:

```powershell
mvnw.cmd clean package
```

O artefato gerado ficará disponível no diretório `target`.
