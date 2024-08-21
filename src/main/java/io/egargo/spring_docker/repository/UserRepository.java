package io.egargo.spring_docker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.dto.UserDTO;
import io.egargo.spring_docker.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Modifying
	@Query("UPDATE User u " +
			"SET u.firstName=:#{#user.firstName}, " +
			"	u.middleName=:#{#user.middleName}, " +
			"	u.lastName=:#{#user.lastName}, " +
			"	u.userName=:#{#user.userName}, " +
			"	u.email=:#{#user.email}, " +
			"	u.password=:#{#user.password} " +
			"WHERE u.id=:id")
	Integer updateUser(@Param("id") Long id, @Param("user") UserCreateDTO user);

	@Query("SELECT " +
			"new io.egargo.spring_docker.dto.UserDTO(" +
			"	u.id, u.firstName, u.middleName, u.lastName, u.userName, u.email" +
			") " +
			"FROM User u " +
			"ORDER BY u.id")
	List<UserDTO> getAllUsers();

	@Query("SELECT " +
			"new io.egargo.spring_docker.dto.UserDTO(" +
			"	u.id, u.firstName, u.middleName, u.lastName, u.userName, u.email" +
			") " +
			"FROM User u " +
			"WHERE u.id = :id")
	Optional<UserDTO> getUserById(@Param("id") Long id);
}
