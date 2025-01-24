package io.egargo.spring_boot_demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class JwtClaim {
	public String userName;
	public String email;
	public String role;
}
