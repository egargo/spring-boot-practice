package io.egargo.spring_docker.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "User")
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public Long id;

	@Column(name = "first_name", nullable = false, unique = false)
	public String firstName;

	@Column(name = "middle_name", nullable = false, unique = false)
	public String middleName;

	@Column(name = "last_name", nullable = false, unique = false)
	public String lastName;

	@Column(name = "user_name", nullable = false, unique = true)
	public String userName;

	@Column(name = "email", nullable = false, unique = true)
	public String email;

	@Column(name = "password", nullable = false, unique = false)
	public String password;

	@Column(name = "date_registered", nullable = false, unique = false)
	public LocalDateTime dateRegistered;
}
