# Customer Assessment

Spring Boot REST API for customer accounts and transactions.

## Features

- Create and retrieve accounts
- Create transactions with operation type validation
- Store debt operations as negative amounts and payments as positive amounts
- Swagger / OpenAPI documentation
- Unit and integration tests
- Docker and Docker Compose support

## Requirements

- Java 21
- Docker and Docker Compose

## Run Locally

### 1. Start PostgreSQL

```bash
docker compose up -d postgres
```

### 2. Start the application

```bash
./gradlew :application:bootRun
```

The API will be available at [http://localhost:8080](http://localhost:8080).

## Run Everything With Docker Compose

This project includes a `docker-compose.yml` that starts both PostgreSQL and the application in the same network.

```bash
docker compose up --build
```

Services:

- Application: [http://localhost:8080](http://localhost:8080)
- PostgreSQL: `localhost:5432`

## Run Tests

Run all tests:

```bash
./gradlew test
```

Integration tests use H2 only in the `test` profile, so they do not require PostgreSQL.

## Build The Jar

Generate the executable jar:

```bash
./gradlew :application:bootJar
```

Generated file:

```bash
application/build/libs/customerassessment.jar
```

## Build Docker Image

The Docker build runs the full test suite before creating the jar. If any test fails, the image build fails.

```bash
docker build -t customerassessment:latest .
```

Run the image manually:

```bash
docker run -p 8080:8080 \
  -e DB_HOST=host.docker.internal \
  -e DB_PORT=5432 \
  -e DB_NAME=customerassessment \
  -e DB_USERNAME=pismo \
  -e DB_PASSWORD=pismo \
  customerassessment:latest
```

## API Documentation

- Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- OpenAPI JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## Main Endpoints

- `POST /accounts`
- `GET /accounts/{accountId}`
- `POST /transactions`

## Environment Variables

The application uses these environment variables for the database connection:

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USERNAME`
- `DB_PASSWORD`

Defaults are defined in `application/src/main/resources/application.yaml`.
