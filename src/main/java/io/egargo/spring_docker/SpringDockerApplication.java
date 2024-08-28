package io.egargo.spring_docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = { "io.egargo.spring_docker.repository" })
@EnableAutoConfiguration
@EntityScan("io.egargo.spring_docker.model")
public class SpringDockerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringDockerApplication.class, args);
	}
}
