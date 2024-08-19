package io.egargo.spring_docker.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "first_name", nullable = false, unique = false)
	private String firstName;

	@Column(name = "middle_name", nullable = false, unique = false)
	private String middleName;

	@Column(name = "last_name", nullable = false, unique = false)
	private String lastName;

	@Column(name = "user_name", nullable = false, unique = true)
	private String userName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false, unique = false)
	private String password;

	@Column(name = "date_registered", nullable = false, unique = false)
	private LocalDateTime dateRegistered;
}
