package io.egargo.spring_docker.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egargo.spring_docker.dto.UserCheckDTO;
import io.egargo.spring_docker.model.UserLogin;
import io.egargo.spring_docker.repository.UserRepository;
import io.egargo.spring_docker.utils.JwtUtil;
import io.egargo.spring_docker.utils.Password;

@Service
public class AuthService {
	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	Password passwordUtil;

	@Autowired
	UserRepository userRepository;

	public Optional<HashMap<String, String>> login(UserLogin userLogin) {
		Optional<UserCheckDTO> user = userRepository.checkUserLogin(userLogin.userName);
		if (user.isEmpty() || (!passwordUtil.verify(userLogin.password, user.get().password))) {
			return Optional.empty();
		}

		final String accessToken = jwtUtil.generateAccessToken(user.get());
		final String refreshToken = jwtUtil.generateRefreshToken(user.get());

		Optional<HashMap<String, String>> map = Optional.of(new HashMap<>());
		map.get().put("accessToken", accessToken);
		map.get().put("refreshToken", refreshToken);
		return map;
	}
}
