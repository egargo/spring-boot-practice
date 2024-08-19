package io.egargo.spring_docker.dto;

import lombok.Data;

@Data
public class UserDTO {
	public Long id;
	public String firstName;
	public String middleName;
	public String lastName;
	public String userName;
	public String email;
}
