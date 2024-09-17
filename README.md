# Apollotech Training: Learning Docker and Docker Compose

Dockerized Spring Boot CRUD API with PostgreSQL

- [x] Spring Security
- [x] Authentication
- [x] Docker
    - [x] Dockerfile
    - [x] docker-compose.yml
- [x] GitHub Actions
    - [x] Workflow file
    - [x] Todo fix H2 in-memory DB
- [x] JWT
- [x] Bcrypt
- [x] DB Migration
- [x] Table relationship


```bash
# Pull the Spring Boot project Docker image from GHCR.
docker pull ghcr.io/egargo/spring-boot-crud

# Copy `env.example` to a new file named `.env`
# then configure the neccessary environment variables.
cp env.example .env

# Build and Spring Boot project and Postgres.
docker compose up -d

# Run tests
mvn clean && mvn -Dtest=SpringDockerApplicationTests test

# Build without running the tests
mvn clean install -Dmaven.test.skip=true -q

# Run the built jar file
javar -jar target/
```
