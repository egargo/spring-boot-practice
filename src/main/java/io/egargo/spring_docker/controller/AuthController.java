package io.egargo.spring_docker.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.egargo.spring_docker.model.UserLogin;
import io.egargo.spring_docker.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthService authService;

	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLogin userLogin) {
		try {
			Optional<HashMap<String, String>> result = authService.login(userLogin);

			if (result.isEmpty()) {
				return new ResponseEntity<>(Collections.singletonMap("message", "Incorrect username and/or password"),
						HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(Collections.singletonMap("data", authService.login(userLogin)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(
					Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
