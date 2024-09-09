package io.egargo.spring_docker;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import io.egargo.spring_docker.model.Savings;
import io.egargo.spring_docker.model.User;
import io.egargo.spring_docker.repository.SavingsRepository;
import io.egargo.spring_docker.repository.UserRepository;

@SpringBootTest
class SpringDockerApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SavingsRepository savingsRepository;

	@Test
	public void createUser() {
		double randomId = Math.random();

		User user = new User();
		user.setFirstName("Test");
		user.setMiddleName("Test");
		user.setLastName("Test");
		user.setUserName("test" + randomId);
		user.setEmail("test" + randomId + "@test.test");
		user.setPassword("Password");
		user.setDateRegistered(LocalDateTime.now());

		userRepository.save(user);

		Assertions.assertNotNull(user.getId());
	}

	@Test
	public void createUserAndBalance() {
		double randomId = Math.random();

		User newUser = new User();
		newUser.setFirstName("Test");
		newUser.setMiddleName("Test");
		newUser.setLastName("Test");
		newUser.setUserName("test" + randomId);
		newUser.setEmail("test" + randomId + "@test.test");
		newUser.setPassword("Password");
		newUser.setDateRegistered(LocalDateTime.now());

		userRepository.save(newUser);
		Long id = newUser.getId();

		Optional<User> user = userRepository.findById(id);
		Savings savings = new Savings();

		double randomBal = Math.random();

		savings.setUser(user.get());
		savings.setBalance(randomBal);

		savingsRepository.save(savings);

		Assertions.assertNotNull(id);
		Assertions.assertNotNull(savings.getId());
	}
}
