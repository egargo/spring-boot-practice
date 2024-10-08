# Spring Boot Practice

(K8s + Docker + Spring Boot + PostgreSQL + CI) Practice


## Tech Stacks

- [Eclipse Temurin 21 (Java)](https://adoptium.net/temurin/)
    - [Spring Boot](https://spring.io/projects/spring-boot)
- [PostgreSQL](https://www.postgresql.org/)
- [Docker](https://docs.docker.com/engine/)
- [Kubernetes](https://kubernetes.io/)
- [Git](https://git-scm.com/)
- [GitHub](https://github.com/)
    - GitHub Actions


## Dependencies

- [curl](https://curl.se/)
- [git](https://git-scm.com/)
- [Insomnia]()/[Postman]()
- [Docker](https://docs.docker.com/engine/)
- [SDKMAN!](https://sdkman.io/)
    - Java Temurin 21
    - Maven


## TODO

- [x] Spring Boot REST API
    - [x] Spring Security
    - [ ] OAuth 2.0
    - [x] Authentication
    - [x] JWT
        - [x] Access Token
        - [x] Refresh Token
    - [x] Bcrypt
    - [x] DB Migration
    - [x] Table relationship
- [Testing]
    - [x] Integration testing
    - [ ] Unit testing
- [x] GitHub Actions
    - [x] Workflow file
    - [x] Todo fix H2 in-memory DB
- [x] Docker
    - [x] Dockerfile
    - [x] docker-compose.yml
- [x] Kubernetes
- [ ] Email
    - [x] Setup SMTP
    - [ ] Send verification email
    - [ ] Send recover account email


## Project Structure

- Spring Boot
    - [`pom.xml`](pom.xml)
    - [`src/main/java/io/egargo/spring_docker/`](src/main/java/io/egargo/spring_docker/)
- Docker
    - [`Dockerfile`](Dockerfile)
    - [`docker-compose.yml`](docker-compose.yml)
- Kubernetes
    - [`deployment.yml`](deployment.yml)
    - [`secrets.yml`](secrets.yml) (Template. Does not contain any configuration values)
- Github Actions
    - [`.github/workflows/`](.github/workflows/)
- Others
    - [`env.example`](env.example)
    - [`Postman.json`](Postman.json)


## Setup


### Initial Setup

Pull the Spring Boot project Docker image from GHCR:
```bash
docker pull ghcr.io/egargo/spring-boot-crud
```

If you are running the project in your host OS, Copy `env.example` to a new
file named `.env` then configure the neccessary environment variables.
Otherwise, configure the secrets.yml file:
```bash
# .env
cp env.example .env

# secrets.yml
vim secrets.yml
```


### Run with Docker

```bash
# Build and Spring Boot project and Postgres
docker compose up -d
```


### Run with K8s

> [!NOTE]
> Access spring-boot api service: <MINIKUBE_IP>:<NODEPORT>

```bash
# Start minikube
minikube start --cpus 4 --memory 4096

# Apply secrets and deployment configuration
kubectl apply -f secrets.yml
kubectl apply -f deployment.yml

# Get the URL of spring-boot service
minikube service spring-boot --url
```


### Run on host OS

```bash
# Build without running the tests and run the built jar file.
mvn clean install -Dmaven.test.skip=true -q
javar -jar target/spring-docker-0.0.1-SNAPSHOT.jar
```


### Run tests

```bash
# Run tests.
mvn -DTest=SpringDockerApplicationTests test
```
