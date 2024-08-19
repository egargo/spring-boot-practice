package io.egargo.spring_docker.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserUpdateDTO {
	public Long id;
	public String firstName;
	public String middleName;
	public String lastName;
	public String userName;
	public String email;
	public String password;
	public LocalDateTime dateRegistered;
}
