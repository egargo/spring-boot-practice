package io.egargo.spring_docker.controller;

import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.model.User;
import io.egargo.spring_docker.mapper.UserDTOMapper;
import io.egargo.spring_docker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserDTOMapper userDTOMapper;

	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<?> getAllUser(@PageableDefault(size = 10, page = 1) Pageable pageable) {
		return userService.getAll(pageable);
	}

	@GetMapping(value = { "/{id}" }, produces = "application/json")
	public ResponseEntity<?> getUserById(@PathVariable Long id) {
		return userService.getById(id);
	}

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createUser(@RequestBody UserCreateDTO userCreateDTO) {
		User user = userDTOMapper.userCreateDTO(userCreateDTO);
		return userService.createUser(user);
	}

	@PutMapping(value = { "", "/" }, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateUser(@RequestParam Long id, @RequestBody UserCreateDTO userCreateDTO) {
		return userService.updateUser(id, userCreateDTO);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}
}
