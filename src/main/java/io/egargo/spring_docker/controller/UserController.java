package io.egargo.spring_docker.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import io.egargo.spring_docker.model.User;
import io.egargo.spring_docker.mapper.UserDTOMapper;
import io.egargo.spring_docker.service.UserService;
import io.egargo.spring_docker.utils.UtilEnumResult;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserDTOMapper userDTOMapper;

	@GetMapping(value = { "", "/" }, produces = "application/json")
	public ResponseEntity<?> getAllUser(@PageableDefault(size = 10, page = 1) Pageable pageable) {
		try {
			return new ResponseEntity<>(Collections.singletonMap("data", userService.getAll(pageable)),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = { "/{id}" }, produces = "application/json")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(Collections.singletonMap("data", userService.getById(id)),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = { "", "/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO) {
		try {
			User user = userDTOMapper.userCreateDTO(userCreateDTO);
			userService.createUser(user);

			return new ResponseEntity<>(Collections.singletonMap("message", "Successfully created user"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = { "", "/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestParam Long id, @RequestBody UserCreateDTO userCreateDTO) {
		try {
			UtilEnumResult u = userService.updateUser(id, userCreateDTO);

			switch (u) {
				case Error:
					return new ResponseEntity<>(Collections.singletonMap("message", "Failed to update user"),
							HttpStatus.INTERNAL_SERVER_ERROR);
				default:
					return new ResponseEntity<>(Collections.singletonMap("message", "Successfully updated user"),
							HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(Collections.singletonMap("message", "Successfully deleted user"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.OK);
		}
	}
}
