# Apollotech Training: Learning Docker and Docker Compose

```bash
# Pull the Spring Boot project Docker image from GHCR.
docker pull ghcr.io/egargo/spring-boot-crud

# Copy `env.example` to a new file named `.env`
# then configure the neccessary environment variables.
cp env.example .env

# Build and Spring Boot project and Postgres.
docker compose up -d
```
