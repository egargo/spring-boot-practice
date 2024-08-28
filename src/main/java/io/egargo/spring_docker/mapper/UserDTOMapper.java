package io.egargo.spring_docker.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.dto.UserDTO;
import io.egargo.spring_docker.dto.UserUpdateDTO;
import io.egargo.spring_docker.model.User;

@Service
public class UserDTOMapper {
	public User userCreateDTO(UserCreateDTO userCreateDTO) {
		User user = new User();

		String hashedPassword = BCrypt.withDefaults().hashToString(12, userCreateDTO.password.toCharArray());

		user.setFirstName(userCreateDTO.firstName);
		user.setMiddleName(userCreateDTO.middleName);
		user.setLastName(userCreateDTO.lastName);
		user.setUserName(userCreateDTO.userName);
		user.setEmail(userCreateDTO.email);
		user.setPassword(hashedPassword);
		user.setDateRegistered(LocalDateTime.now());

		return user;
	}
}
