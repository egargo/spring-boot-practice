package io.egargo.spring_docker.utils;

import io.egargo.spring_docker.model.JwtClaim;
import io.egargo.spring_docker.model.JwtType;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {
	@Value("${security.jwt.secret-key}")
	private String tokenSecretKey;

	@Value("${security.jwt.access-expiration-time}")
	private Long tokenAccessExp;

	@Value("${security.jwt.refresh-expiration-time}")
	private Long tokenRefreshExp;

	@Value("${security.jwt.issuer}")
	private String tokenIssuer;

	@Value("${security.jwt.subject}")
	private String tokenSubject;

	public String generateToken(JwtType type, final JwtClaim claim) {
		Long exp = (type == JwtType.Access) ? tokenAccessExp : tokenRefreshExp;

		return Jwts.builder()
				.issuer(tokenIssuer)
				.subject(tokenSubject)
				.expiration(new Date(System.currentTimeMillis() + exp))
				.notBefore(new Date())
				.issuedAt(new Date(System.currentTimeMillis()))
				.id(UUID.randomUUID().toString())
				.claim("data", claim)
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
