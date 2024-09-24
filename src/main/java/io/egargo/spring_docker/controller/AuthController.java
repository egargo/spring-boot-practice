package io.egargo.spring_docker.controller;

import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.mapper.UserDTOMapper;
import io.egargo.spring_docker.model.User;
import io.egargo.spring_docker.model.UserLogin;
import io.egargo.spring_docker.service.AuthService;
import io.egargo.spring_docker.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthService authService;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	UserDTOMapper userDTOMapper;

	@PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> singup(@RequestBody UserCreateDTO userCreateDTO) {
		try {
			User user = userDTOMapper.userCreateDTO(userCreateDTO);
			return authService.signup(user);
		} catch (Exception e) {
			return new ResponseEntity<>(
					Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> login(@RequestBody UserLogin userLogin) {
		try {
			return authService.login(userLogin);
		} catch (Exception e) {
			return new ResponseEntity<>(
					Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/refresh", produces = "application/json")
	public ResponseEntity<?> refresh(@RequestHeader HashMap<String, String> header) {
		try {
			return authService.refresh(header.get("refreshtoken"));
		} catch (Exception e) {
			return new ResponseEntity<>(
					Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
