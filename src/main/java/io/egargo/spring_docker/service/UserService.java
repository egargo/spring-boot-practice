package io.egargo.spring_docker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.google.protobuf.Option;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.dto.UserDTO;
import io.egargo.spring_docker.dto.UserUpdateDTO;
import io.egargo.spring_docker.entity.User;
import io.egargo.spring_docker.mapper.UserDTOMapper;
import io.egargo.spring_docker.repository.UserRepository;

@Service()
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDTOMapper userDTOMapper;

	public Iterable<User> getAll() {
		return userRepository.findAll();
	}

	public Optional<User> getById(Long id) {
		return userRepository.findById(id);
	}

	public void createUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(Long id, UserCreateDTO userCreateDTO) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			UserUpdateDTO userUpdateDTO = new UserUpdateDTO();

			userUpdateDTO.setDateRegistered(user.get().getDateRegistered());

			if (userCreateDTO.firstName == null)
				userUpdateDTO.setFirstName(userCreateDTO.firstName);
			else
				userUpdateDTO.setFirstName(user.get().getFirstName());

			if (userCreateDTO.middleName == null)
				userUpdateDTO.setMiddleName(userCreateDTO.middleName);
			else
				userUpdateDTO.setMiddleName(user.get().getMiddleName());

			if (userCreateDTO.lastName == null)
				userUpdateDTO.setLastName(userCreateDTO.lastName);
			else
				userUpdateDTO.setLastName(user.get().getLastName());

			if (userCreateDTO.userName == null)
				userUpdateDTO.setUserName(userCreateDTO.userName);
			else
				userUpdateDTO.setUserName(user.get().getUserName());

			if (userCreateDTO.email == null)
				userUpdateDTO.setEmail(userCreateDTO.email);
			else
				userUpdateDTO.setEmail(user.get().getEmail());

			if (userCreateDTO.password == null)
				userUpdateDTO.setPassword(userCreateDTO.password);
			else
				userUpdateDTO.setPassword(user.get().getPassword());

			User up = userDTOMapper.userCreateDTO(userCreateDTO);
			up.setId(user.get().getId());
			userRepository.save(up);
		} else {
			System.out.println("Error");
		}
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
