package io.egargo.spring_docker.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.egargo.spring_docker.model.UserLogin;
import io.egargo.spring_docker.repository.UserRepository;
import io.egargo.spring_docker.utils.JwtUtil;
import io.egargo.spring_docker.utils.Password;

@Controller
public class AuthService {
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	Password passwordUtil;

	@Autowired
	UserRepository userRepository;

	public Optional<HashMap<String, String>> login(UserLogin userLogin) {
		Optional<String> user = userRepository.checkUserLogin(userLogin.userName);
		Optional<HashMap<String, String>> map = Optional.of(new HashMap<>());

		if (user.isEmpty() || (!passwordUtil.verify(userLogin.password, user.get()).verified)) {
			return Optional.empty();
		}

		final String accessToken = jwtUtil.generateAccessToken(userLogin);
		final String refreshToken = jwtUtil.generateRefreshToken(userLogin);

		map.get().put("accessToken", accessToken);
		map.get().put("refreshToken", refreshToken);

		return map;
	}
}
