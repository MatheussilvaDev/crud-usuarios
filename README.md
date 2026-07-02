# 👤 CRUD de Usuários API

> API REST para cadastro, autenticação, consulta, atualização e remoção de usuários, desenvolvida como projeto de estudo com foco em **Java + Spring Boot**, segurança com JWT e persistência em PostgreSQL.

---

## 📌 Sobre o Projeto

Este projeto foi construído como uma aplicação backend para gerenciamento de usuários. A proposta é consolidar conhecimentos em desenvolvimento de APIs com Spring, organização em camadas, autenticação stateless e integração entre serviços.

Atualmente, a API permite:

- cadastrar usuários
- autenticar usuários com JWT
- consultar usuários por e-mail, id e listagem geral
- atualizar os dados do usuário autenticado
- remover usuários por e-mail

---

## 🎯 Objetivos do Projeto

- Praticar a construção de APIs REST com Java 21 e Spring Boot
- Aplicar autenticação e autorização com JWT
- Utilizar PostgreSQL com Spring Data JPA para persistência dos usuários
- Estruturar o projeto com separação entre controller, service, repository, DTO e entity
- Servir como base de autenticação e consulta de usuários para outros serviços do ecossistema

---

## 🛠️ Stack Técnica

| Categoria | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 4 |
| Web | Spring Web MVC |
| Segurança | Spring Security + JWT |
| Persistência | Spring Data JPA |
| Banco de dados | PostgreSQL |
| Build Tool | Gradle |
| Utilitários | Lombok |

---

## 🏗️ Arquitetura

O projeto segue uma estrutura em camadas, separando responsabilidades de entrada, regra de negócio, persistência, segurança e contratos da API.

```text
src/main/java/com/mathdev/usuario
├── business
│   ├── dto         # Objetos de entrada e saída
│   ├── mapper      # Conversão entre DTO e entidade
│   └── service     # Regras de negócio
├── controller      # Endpoints REST
├── infrastructure
│   ├── entity      # Entidades persistidas no PostgreSQL
│   ├── exception   # Exceções de domínio e negócio
│   ├── repository  # Acesso a dados
│   └── security    # Configuração JWT e autenticação
```

---

## ✅ Funcionalidades Atuais

- Cadastro de usuários com senha criptografada usando BCrypt
- Login com geração de token JWT
- Consulta de usuário por e-mail
- Consulta de usuário por identificador
- Listagem de todos os usuários cadastrados
- Atualização do usuário autenticado com base no token enviado no header
- Remoção de usuário por e-mail
- Cadastro de endereços e telefones vinculados ao usuário

---

## 🔐 Segurança

A API utiliza autenticação baseada em JWT.

Rotas públicas:

- `POST /usuario`
- `POST /usuario/login`

Rotas protegidas:

- Demais endpoints sob `/usuario/**`

O token JWT deve ser enviado no header:

```http
Authorization: Bearer seu_token_aqui
```

---

## 🔗 Dependência no Ecossistema

Este projeto faz parte de um ecossistema maior e funciona como serviço de identidade e gerenciamento de usuários.

- Serviço principal deste repositório: API de usuários
- Serviço consumidor relacionado: API de agendamento de tarefas
- Responsabilidade desta API: autenticação, cadastro, consulta e atualização de usuários
- Dependência entre projetos: o `agendador-tarefas` depende do `crud-usuario` para validar o usuário autenticado e consumir seus dados
- Repositório do projeto Agendador de Taregas: [Agendador de Tarefas](https://github.com/MatheussilvaDev/agendador-tarefas.git)

---

## 🌐 Endpoints Principais

| Método | Rota | Descrição |
|---|---|---|
| `POST` | `/usuario` | Cadastra um novo usuário |
| `POST` | `/usuario/login` | Autentica o usuário e retorna um token JWT |
| `PUT` | `/usuario` | Atualiza o usuário autenticado |
| `GET` | `/usuario?email={email}` | Busca usuário por e-mail |
| `GET` | `/usuario/{id}` | Busca usuário por id |
| `GET` | `/usuario/all` | Lista todos os usuários |
| `DELETE` | `/usuario/{email}` | Remove usuário por e-mail |

### Exemplo de payload para cadastro

```json
{
  "nome": "Matheus Silva",
  "email": "matheus@email.com",
  "senha": "123456",
  "enderecos": [
    {
      "estado": "SP",
      "cidade": "Campinas",
      "bairro": "Centro",
      "cep": "13010-000",
      "rua": "Rua das Flores",
      "numero": 123,
      "complemento": "Apto 12"
    }
  ],
  "telefones": [
    {
      "ddd": "19",
      "numero": "999999999"
    }
  ]
}
```

### Exemplo de payload para login

```json
{
  "email": "matheus@email.com",
  "senha": "123456"
}
```

---

## ⚙️ Configuração

As propriedades atuais da aplicação estão em `src/main/resources/application.properties`:

```properties
spring.application.name=usuario
spring.datasource.url=jdbc:postgresql://localhost:5432/javanauta
spring.datasource.username=postgres
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

Se necessário, ajuste a URL do PostgreSQL, usuário e senha conforme o seu ambiente local.

---

## 🚀 Como Rodar o Projeto Localmente

### Pré-requisitos

- Java 21
- PostgreSQL em execução local
- Banco de dados `javanauta` criado
- Gradle Wrapper do projeto

### Passos

```bash
# Clonar o repositório
git clone https://github.com/MatheussilvaDev/crud-usuarios.git

# Entrar na pasta do projeto
cd crud-usuario

# Executar os testes
./gradlew test

# Subir a aplicação
./gradlew bootRun
```

A aplicação ficará disponível em:

```text
http://localhost:8080
```

---

## 🗺️ Roadmap do Projeto

| Fase | Descrição | Status |
|---|---|---|
| 0 — Fundação | Estrutura inicial, camadas da aplicação e persistência básica | ✅ Concluído |
| 1 — CRUD de usuários | Cadastro, consulta, atualização e remoção | ✅ Concluído |
| 2 — Segurança | Login e proteção de rotas com JWT | ✅ Concluído |
| 3 — Integração | Consumo por outros serviços, como o agendador de tarefas | 🟡 Em andamento |
| 4 — Evolução | Tratamento global de erros, documentação e testes mais completos | 🔲 Não iniciado |

> 🔲 Não iniciado · 🟡 Em andamento · ✅ Concluído

---

## ✍️ Sobre o Autor

Projeto desenvolvido por **Matheus Silva** como parte do aprofundamento prático em desenvolvimento backend com Java e Spring.

- LinkedIn: https://www.linkedin.com/in/matheus-henrique-093207315

---

## 📃 Licença

Este projeto é de uso educacional e pode ser utilizado como referência de estudo.
