package io.egargo.spring_boot_demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJpaRepositories(basePackages = { "io.egargo.spring_boot_demo.repository" })
@EnableAutoConfiguration
@EntityScan("io.egargo.spring_boot_demo.model")
public class SpringBootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

	@GetMapping(path = "/")
	public ResponseEntity<?> index() {
		return new ResponseEntity<>(Collections.singletonMap("message", "OK"), HttpStatus.OK);
	}
}
