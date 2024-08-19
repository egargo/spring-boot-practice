package io.egargo.spring_docker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.dto.UserDTO;
import io.egargo.spring_docker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// @Modifying
	// @Query("UPDATE User u " +
	// "SET u.firstName=:#{#user.getName()}, " +
	// " u.middleName=:#{#user.getMiddleName()}, " +
	// " u.lastName=:#{#user.getLastName()}, " +
	// " u.userName=:#{#user.getUsername()}, " +
	// " u.email=:#{#user.getEmail()}, " +
	// " u.password=:#{#user.getPassword()} " +
	// "WHERE u.id=:#{#user.getId()}")
	// void updateUser(@Param("user") UserCreateDTO user);

	// @Query("SELECT " +
	// // "u "+
	// "u.firstName, u.middleName, u.lastName, u.userName, u.email " +
	// // "u.getFirstName(), u.getMiddleName(), u.getLastName(), u.getUserName(),
	// // u.getEmail() " +
	// "FROM User u " +
	// "ORDER BY id")
	// List<UserDTO> findAllUsersCustom();
}
