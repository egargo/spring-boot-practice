package io.egargo.spring_docker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.favre.lib.crypto.bcrypt.BCrypt;

import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.dto.UserDTO;
import io.egargo.spring_docker.model.User;
import io.egargo.spring_docker.mapper.UserDTOMapper;
import io.egargo.spring_docker.repository.UserRepository;
import io.egargo.spring_docker.utils.UtilEnumResult;

@Service()
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDTOMapper userDTOMapper;

	public List<UserDTO> getAll(Pageable pageable) {
		return userRepository.getAllUsers(pageable).getContent();
	}

	public Optional<UserDTO> getById(Long id) {
		return userRepository.getUserById(id);
	}

	public void createUser(User user) {
		userRepository.save(user);
	}

	@Transactional
	public UtilEnumResult updateUser(Long id, UserCreateDTO userCreateDTO) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			return UtilEnumResult.Error;
		}

		userCreateDTO.password = BCrypt.withDefaults().hashToString(12, userCreateDTO.password.toCharArray());
		userRepository.updateUser(id, userCreateDTO);
		return UtilEnumResult.Ok;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
