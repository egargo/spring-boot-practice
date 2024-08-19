FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean install -Dmaven.test.skip=true -q


FROM alpine AS deps
RUN apk --no-cache add \
	curl \
	tar \
	wget
RUN curl -sO https://download2.gluonhq.com/openjfx/21.0.4/openjfx-21.0.4_linux-x64_bin-sdk.zip
RUN wget https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21.0.4%2B7/OpenJDK21U-jre_x64_linux_hotspot_21.0.4_7.tar.gz
RUN unzip openjfx-21.0.4_linux-x64_bin-sdk.zip
RUN tar xf OpenJDK21U-jre_x64_linux_hotspot_21.0.4_7.tar.gz
RUN rm openjfx-21.0.4_linux-x64_bin-sdk.zip \
	OpenJDK21U-jre_x64_linux_hotspot_21.0.4_7.tar.gz


FROM gcr.io/distroless/base-debian12
WORKDIR /app
COPY --from=build /target/spring-docker-0.0.1-SNAPSHOT.jar app.jar
COPY --from=deps /jdk-21.0.4+7-jre /app/jdk-21
EXPOSE 8888
ENTRYPOINT [ "/app/jdk-21/bin/java", "-jar", "/app/app.jar" ]
