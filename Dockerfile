FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean install -Dmaven.test.skip=true -q


FROM alpine AS deps
RUN apk --no-cache add tar wget
RUN wget https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.4%2B7/OpenJDK21U-jre_x64_alpine-linux_hotspot_21.0.4_7.tar.gz
RUN tar xf OpenJDK21U-jre_x64_alpine-linux_hotspot_21.0.4_7.tar.gz
RUN rm OpenJDK21U-jre_x64_alpine-linux_hotspot_21.0.4_7.tar.gz


FROM alpine
WORKDIR /app
COPY --from=build /target/spring-docker-0.0.1-SNAPSHOT.jar app.jar
COPY --from=deps /jdk-21.0.4+7-jre /app/jre-21
EXPOSE 28279
ENTRYPOINT [ "/app/jre-21/bin/java", "-jar", "/app/app.jar" ]
