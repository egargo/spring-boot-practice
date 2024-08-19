package io.egargo.spring_docker.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
	public String firstName;
	public String middleName;
	public String lastName;
	public String userName;
	public String email;
	public String password;
}
