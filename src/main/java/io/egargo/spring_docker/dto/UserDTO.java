package io.egargo.spring_docker.dto;

public class UserDTO {
	public Long id;
	public String firstName;
	public String middleName;
	public String lastName;
	public String userName;
	public String email;

	public UserDTO(Long id, String firstName, String middleName, String lastName, String userName, String email) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
	}
}
