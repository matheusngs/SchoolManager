# 📚 School Manager

Sistema de gerenciamento escolar desenvolvido com **Java + Spring Boot + PostgreSQL**.

## 🔧 Tecnologias
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger/OpenAPI
- Maven

## 🚀 Funcionalidades
- Cadastro de estudantes com DTOs, validações e tratamento de exceções
- Relacionamentos entre Aluno e Turma (Em andamento: cadastro do professor)
- Integração com banco de dados PostgreSQL
- Documentação da API com Swagger

## 📂 Estrutura
- `entity/`: classes que representam as tabelas do banco
- `repository/`: interfaces para acesso ao banco
- `service/`: regras de negócio
- `controller/`: endpoints da API
- `dto/`: objetos de transferência de dados

## 📦 Como rodar o projeto

```bash
# Clone o repositório
git clone https://github.com/matheusngs/SchoolManager.git

# Entre na pasta
cd school-manager

# Configure o application.properties com seu banco local
spring.datasource.url=jdbc:postgresql://localhost:5432/schoolmanager
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Rode com sua IDE ou via terminal
./mvnw spring-boot:run
