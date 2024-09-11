package io.egargo.spring_docker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class JwtClaim {
	public String userName;
	public String email;
	public String role;
}
