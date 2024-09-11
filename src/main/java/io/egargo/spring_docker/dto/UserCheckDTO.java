package io.egargo.spring_docker.dto;

import io.egargo.spring_docker.model.UserRole;

public class UserCheckDTO {
	public String userName;
	public String email;
	public UserRole role;
	public String password;

	public UserCheckDTO(String userName, String email, UserRole role, String password) {
		this.userName = userName;
		this.email = email;
		this.role = role;
		this.password = password;
	}
}
