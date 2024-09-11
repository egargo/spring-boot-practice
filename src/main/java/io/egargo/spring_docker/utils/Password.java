package io.egargo.spring_docker.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class Password {
	public String hash(final String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean verify(final String passwordPlain, final String passwordHash) {
		return BCrypt.checkpw(passwordPlain, passwordHash);
	}
}
