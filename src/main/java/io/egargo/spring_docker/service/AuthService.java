package io.egargo.spring_docker.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.egargo.spring_docker.dto.UserCheckDTO;
import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.mapper.UserDTOMapper;
import io.egargo.spring_docker.model.JwtClaim;
import io.egargo.spring_docker.model.User;
import io.egargo.spring_docker.model.UserLogin;
import io.egargo.spring_docker.repository.UserRepository;
import io.egargo.spring_docker.utils.Email;
import io.egargo.spring_docker.utils.JwtUtil;
import io.egargo.spring_docker.utils.Password;
import java.util.Collections;

@Service
public class AuthService {
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	Password passwordUtil;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDTOMapper userDTOMapper;

	@Autowired
	Email email;

	public ResponseEntity<?> signup(User user) {
		try {
			userRepository.save(user);
			email.sendMail(user.email, "Signup", "Signup");

			return new ResponseEntity<>(Collections.singletonMap("message", "Successfully created user"),
					HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "Username and/or email is already in use"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<?> login(UserLogin userLogin) {
		Optional<UserCheckDTO> user = userRepository.checkUserLogin(userLogin.userName);

		if (user.isEmpty() || (!passwordUtil.verify(userLogin.password, user.get().password))) {
			return new ResponseEntity<>(Collections.singletonMap("message", "Incorrect username and/or password"),
					HttpStatus.NOT_FOUND);
		}

		final JwtClaim claim = new JwtClaim();
		claim.setUserName(user.get().userName);
		claim.setEmail(user.get().email);
		claim.setRole(user.get().role.toString());

		final String accessToken = jwtUtil.generateAccessToken(claim);
		final String refreshToken = jwtUtil.generateRefreshToken(claim);

		Optional<HashMap<String, String>> map = Optional.of(new HashMap<>());
		map.get().put("accessToken", accessToken);
		map.get().put("refreshToken", refreshToken);

		return new ResponseEntity<>(Collections.singletonMap("data", map), HttpStatus.OK);
	}

	public ResponseEntity<?> refresh(String refreshToken) {
		if (!jwtUtil.verifyToken(refreshToken)) {
			return new ResponseEntity<>(Collections.singletonMap("message", "Access token is expired and/or invalid"),
					HttpStatus.UNAUTHORIZED);
		}

		ObjectMapper map = new ObjectMapper();
		map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		JwtClaim claim = map.convertValue(jwtUtil.extractAllClaims(refreshToken).get("data"), JwtClaim.class);

		return new ResponseEntity<>(Collections.singletonMap("accessToken", jwtUtil.generateAccessToken(claim)),
				HttpStatus.OK);
	}
}
