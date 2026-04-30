# 📋 API REST de Tarefas — Minicurso Rocketseat

API REST desenvolvida com Java e Spring Boot durante o Minicurso da Rocketseat.

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- BCrypt
- Maven

## 🔒 Funcionalidades

- Cadastro de usuários com senha criptografada (BCrypt)
- Autenticação via Basic Auth
- Filtro de segurança (OncePerRequestFilter)
- CRUD completo de tarefas
- Validação de datas
- Validação de permissão por usuário
- Tratamento global de erros (@ControllerAdvice)

## 🔗 Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /users/boasvindas | Cadastrar usuário |
| POST | /tasks/ | Criar tarefa |
| GET | /tasks/ | Listar tarefas do usuário |
| PUT | /tasks/{id} | Atualizar tarefa |

