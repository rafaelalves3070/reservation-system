# Reservation System

Sistema de reservas de salas desenvolvido com Java e Spring Boot, permitindo cadastrar usuários, salas e realizar reservas com validação de conflito de horário.

## Objetivo

Este projeto foi desenvolvido para demonstrar conhecimentos em:

* Java
* Spring Boot
* API REST
* JPA / Hibernate
* PostgreSQL
* Validação de dados
* Tratamento de exceções
* Testes unitários com JUnit e Mockito
* Documentação de API com Swagger

---

## Funcionalidades

### Usuários

* Cadastrar usuário
* Listar usuários

### Salas

* Cadastrar sala
* Listar salas

### Reservas

* Criar reserva
* Validar se usuário existe
* Validar se sala existe
* Impedir reservas com conflito de horário

---

## Tecnologias Utilizadas

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Hibernate
* Maven
* JUnit 5
* Mockito
* Swagger / OpenAPI

---

## Estrutura do Projeto

```text
src
├── main
│   ├── java/com/r/reservation_system
│   │   ├── controller
│   │   ├── dto
│   │   ├── entity
│   │   ├── exception
│   │   ├── repository
│   │   └── service
│   └── resources
└── test
    └── java/com/r/reservation_system
```

---

## Endpoints

### Usuários

```http
GET /users
POST /users
```

Exemplo de requisição:

```json
{
  "name": "Rafael",
  "email": "rafael@email.com"
}
```

### Salas

```http
GET /rooms
POST /rooms
```

Exemplo de requisição:

```json
{
  "name": "Sala 1",
  "capacity": 10,
  "location": "Primeiro Andar"
}
```

### Reservas

```http
POST /reservations
```

Exemplo de requisição:

```json
{
  "userId": 1,
  "roomId": 1,
  "startDateTime": "2026-04-03T14:00:00",
  "endDateTime": "2026-04-03T16:00:00"
}
```

---

## Regra de Negócio

O sistema não permite criar uma reserva caso já exista outra reserva para a mesma sala no mesmo horário.

Exemplo:

* Reserva existente: 14:00 às 16:00
* Nova tentativa: 15:00 às 17:00
* Resultado: reserva bloqueada

Mensagem retornada:

```text
There is already a reservation for this room at the selected time.
```

---

## Como Executar

### 1. Clonar o repositório

```bash
git clone <url-do-repositorio>
```

### 2. Configurar o PostgreSQL

Criar um banco chamado:

```text
reservation_system
```

### 3. Configurar o application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/reservation_system
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Executar a aplicação

```bash
mvn spring-boot:run
```

---

## Documentação Swagger

Após iniciar a aplicação, acessar:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Testes

O projeto possui testes unitários utilizando JUnit e Mockito para validar as regras de negócio.

Exemplo de cenário testado:

* tentativa de criar reserva em horário já ocupado
* sistema lança exceção corretamente

Executar testes:

```bash
mvn test
```

---

## Melhorias Futuras

* Cancelamento de reservas
* Atualização de reservas
* Autenticação de usuários
* Controle de permissões
* Paginação nas listagens
* Testes de integração
* Docker para facilitar execução do banco

---

## Autor

Rafael Alves

Estudante de Ciência da Computação na FIAP, com foco em desenvolvimento back-end utilizando Java, Spring Boot, APIs REST e banco de dados.
