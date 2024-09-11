package io.egargo.spring_docker.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.dto.UserDTO;
import io.egargo.spring_docker.model.User;
import io.egargo.spring_docker.mapper.UserDTOMapper;
import io.egargo.spring_docker.repository.UserRepository;
import io.egargo.spring_docker.utils.Password;

@Service()
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDTOMapper userDTOMapper;

	public ResponseEntity<?> getAll(Pageable pageable) {
		List<UserDTO> users = userRepository.getAllUsers(pageable).getContent();

		try {
			if (users.isEmpty()) {
				return new ResponseEntity<>(Collections.singletonMap("message", "There are no users found"),
						HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(Collections.singletonMap("data", users), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> getById(Long id) {
		Optional<UserDTO> user = userRepository.getUserById(id);

		try {
			if (user.isEmpty()) {
				return new ResponseEntity<>(Collections.singletonMap("message", "User not found"),
						HttpStatus.NOT_FOUND);
			}

			return new ResponseEntity<>(Collections.singletonMap("data", user), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> createUser(User user) {
		try {
			userRepository.save(user);
			return new ResponseEntity<>(Collections.singletonMap("message", "Successfully created user"),
					HttpStatus.CREATED);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "Username and/or email is already in use"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Transactional
	public ResponseEntity<?> updateUser(Long id, UserCreateDTO userCreateDTO) {
		try {
			Optional<User> user = userRepository.findById(id);

			if (user.isEmpty()) {
				return new ResponseEntity<>(Collections.singletonMap("message", "Failed to update user"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			userCreateDTO.password = new Password().hash(userCreateDTO.password);
			userRepository.updateUser(id, userCreateDTO);
			return new ResponseEntity<>(Collections.singletonMap("message", "Successfully updated user"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<?> deleteUser(Long id) {
		try {
			Optional<User> user = userRepository.findById(id);

			if (user.isEmpty()) {
				return new ResponseEntity<>(Collections.singletonMap("message", "Failed to delete user"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

			userRepository.deleteById(id);
			return new ResponseEntity<>(Collections.singletonMap("message", "Successfully deleted user"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.OK);
		}
	}

}
