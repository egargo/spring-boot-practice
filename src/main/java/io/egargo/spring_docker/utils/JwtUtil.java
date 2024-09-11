package io.egargo.spring_docker.utils;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.egargo.spring_docker.dto.UserCheckDTO;
import io.egargo.spring_docker.model.JwtClaim;
import io.egargo.spring_docker.model.User;
import io.egargo.spring_docker.model.UserLogin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	@Value("${security.jwt.secret-key}")
	private String tokenSecretKey;

	@Value("${security.jwt.access-expiration-time}")
	private long tokenAccessExp;

	@Value("${security.jwt.refresh-expiration-time}")
	private long tokenRefreshExp;

	public String generateAccessToken(final UserCheckDTO user) {
		final JwtClaim claim = new JwtClaim();
		claim.setUserName(user.userName);
		claim.setEmail(user.email);
		claim.setRole(user.role.toString());

		return Jwts.builder()
				.claim("data", claim)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + tokenAccessExp))
				.signWith(this.getSignedKey()).compact().toString();
	}

	public String generateRefreshToken(final UserCheckDTO user) {
		final JwtClaim claim = new JwtClaim();
		claim.setUserName(user.userName);
		claim.setEmail(user.email);
		claim.setRole(user.role.toString());

		return Jwts.builder()
				.claim("data", claim)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + tokenRefreshExp))
				.signWith(this.getSignedKey())
				.compact();
	}

	public Claims extractAllClaims(final String token) {
		return Jwts.parser()
				.verifyWith(this.getSignedKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean verifyToken(final String token) {
		return !extractAllClaims(token).getExpiration().before(new Date());
	}

	private SecretKey getSignedKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(tokenSecretKey));
	}
}
