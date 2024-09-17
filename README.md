# Spring Boot Practice

Dockerized Spring Boot CRUD API with PostgreSQL

## Stacks

- Spring Boot
- PostgreSQL
- Docker
- Git, GitHub, GitHub Actions

## TODO

- [x] Spring Security
- [x] Authentication
- [x] Docker
    - [x] Dockerfile
    - [x] docker-compose.yml
- [x] GitHub Actions
    - [x] Workflow file
    - [x] Todo fix H2 in-memory DB
- [x] JWT
    - [x] Access Token
    - [x] Refresh Token
- [x] Bcrypt
- [x] DB Migration
- [x] Table relationship
- [ ] Kubernetes


## Setup

```bash
# Pull the Spring Boot project Docker image from GHCR.
docker pull ghcr.io/egargo/spring-boot-crud

# Copy `env.example` to a new file named `.env`
# then configure the neccessary environment variables.
cp env.example .env

# Build and Spring Boot project and Postgres.
docker compose up -d


# Run tests
mvn -DTest=SpringDockerApplicationTests test


# Build without running the tests
mvn clean install -Dmaven.test.skip=true -q

# Run the built jar file
javar -jar target/spring-docker-0.0.1-SNAPSHOT.jar
```
