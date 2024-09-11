package io.egargo.spring_docker.configuration;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.egargo.spring_docker.model.JwtClaim;
import io.egargo.spring_docker.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final JwtUtil jwtUtil;

	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String authorizationHeader = httpServletRequest.getHeader("Authorization");
		String refreshToken = httpServletRequest.getHeader("refreshToken");

		ObjectMapper map = new ObjectMapper();
		map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String token = authorizationHeader.split("Bearer ")[2].trim();
			String username = map.convertValue(jwtUtil.extractAllClaims(token).get("data"), JwtClaim.class)
					.getUserName();

			if (username != null & jwtUtil.verifyToken(token) == true) {
				SecurityContextHolder.getContext()
						.setAuthentication(new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>()));
			}
		}

		chain.doFilter(request, response);
	}
}
