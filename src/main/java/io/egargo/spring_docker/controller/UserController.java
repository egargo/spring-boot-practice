package io.egargo.spring_docker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.entity.User;
import io.egargo.spring_docker.mapper.UserDTOMapper;
import io.egargo.spring_docker.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserDTOMapper userDTOMapper;

	@GetMapping(value = { "", "/" }, produces = "application/json")
	public ResponseEntity<?> getAllUser() {
		try {
			return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An unexpected error occurred: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = { "/{id}" }, produces = "application/json")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = { "", "/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO) {
		try {
			User user = userDTOMapper.userCreateDTO(userCreateDTO);
			userService.createUser(user);

			return new ResponseEntity<>("Successfully created user", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = { "", "/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestParam Long id, @RequestBody UserCreateDTO userCreateDTO) {
		try {
			userService.updateUser(id, userCreateDTO);
			return new ResponseEntity<>("Successfully updated user", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = { "", "/{id}" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>("Successfully deleted user", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
