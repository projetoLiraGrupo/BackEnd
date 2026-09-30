# Lira API — Backend

Backend da plataforma **Lira Paulistana**, desenvolvido como uma API REST com Java e Spring Boot.

O projeto centraliza regras de negócio, persistência de dados, autenticação e integrações utilizadas pela aplicação, mantendo o frontend desacoplado do backend.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT (`java-jwt`)
- Bean Validation
- MapStruct
- Maven
- H2 Database para desenvolvimento
- Microsoft SQL Server JDBC Driver
- Swagger / OpenAPI
- AWS SDK para S3
- AWS SDK para Lambda

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
│   │   │   │   |   └──aws/
│   │   │   │   ├── entity/
│   │   │   │   │   └── dto/
|   |   |   |   |       └── response/
│   │   │   │   ├── exception/
│   │   │   │   ├── mapper/
│   │   │   │   ├── repository/
│   │   │   │   └── service/
│   │   │   └── resources/
│   │   │       └── application.properties
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

Os DTOs são utilizados para controlar os dados recebidos e retornados pela API, enquanto os mappers realizam a conversão entre DTOs e entidades.

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

## Banco de dados

No ambiente atual de desenvolvimento, a aplicação utiliza um banco H2 em memória:

```properties
spring.datasource.url=jdbc:h2:mem:teste
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driver-class-name=org.h2.Driver
```

O banco é recriado quando a aplicação é iniciada porque o Hibernate está configurado com:

```properties
spring.jpa.hibernate.ddl-auto=create-drop
```

> O H2 é adequado para desenvolvimento e testes. Para ambientes persistentes, configure um banco externo, como SQL Server.

## H2 Console

Com a aplicação em execução:

```text
http://localhost:8080/h2-console
```

Configuração padrão:

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:teste` |
| User Name | `sa` |
| Password | vazio |

## Swagger / OpenAPI

A documentação interativa da API pode ser acessada em:

```text
http://localhost:8080/swagger-ui.html
```

A especificação OpenAPI também pode ser consultada em:

```text
http://localhost:8080/v3/api-docs
```

## Autenticação e segurança

A API utiliza **Spring Security** e autenticação baseada em **JWT**.

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

O cadastro de aluno e a rota de login são públicos.

As rotas de aluno atualmente também possuem liberações temporárias para testes no `SecurityConfigurations`. Essas permissões devem ser revisadas antes de colocar a aplicação em produção.

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

As requisições são validadas pela API antes de serem processadas.

## Validações

Os DTOs utilizam Jakarta Bean Validation para validar informações recebidas pela API.

Entre as validações podem existir regras como:

- campos obrigatórios;
- tamanho mínimo e máximo;
- e-mail válido;
- formato de CPF;
- datas válidas;
- relacionamentos existentes;
- prevenção de e-mail ou CPF duplicado.

Erros relacionados a recursos inexistentes ou conflitos de cadastro são tratados pela camada de aplicação.

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

Em produção, configure uma chave própria para:

```properties
api.security.token.secret
```

Exemplo usando variável de ambiente:

```bash
export API_SECURITY_TOKEN_SECRET="uma-chave-segura-e-privada"
```

> Não utilize chaves de desenvolvimento, senhas ou tokens reais diretamente no código-fonte ou no Git.

## AWS

O projeto possui dependências para integração com:

- Amazon S3
- AWS Lambda

Configurações existentes:

```properties
aws.s3.bucket-name=bucket
aws.s3.region=us-east-1
aws.access-key=AWS_ACCESS_KEY
aws.secret-key=AWS_SECRET_KEY
```

Para desenvolvimento,
