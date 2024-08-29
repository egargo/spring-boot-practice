package io.egargo.spring_docker.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Password {
	public String hash(String password) {
		return BCrypt.withDefaults().hashToString(12, password.toCharArray());
	}

	public BCrypt.Result verify(String passwordPlain, String passwordHash) {
		return BCrypt.verifyer().verify(passwordPlain.toCharArray(), passwordHash);
	}
}
