package io.egargo.spring_boot_demo.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import io.egargo.spring_boot_demo.dto.UserCreateDTO;
import io.egargo.spring_boot_demo.model.User;
import io.egargo.spring_boot_demo.model.UserRole;
import io.egargo.spring_boot_demo.utils.Password;

@Service
public class UserDTOMapper {
	public User userCreateDTO(UserCreateDTO userCreateDTO) {
		User user = new User();

		user.setFirstName(userCreateDTO.firstName);
		user.setMiddleName(userCreateDTO.middleName);
		user.setLastName(userCreateDTO.lastName);
		user.setUserName(userCreateDTO.userName);
		user.setEmail(userCreateDTO.email);
		user.setRole(UserRole.Member);
		user.setPassword(new Password().hash(userCreateDTO.password));
		user.setDateRegistered(LocalDateTime.now());

		return user;
	}
}
