package io.egargo.spring_docker.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.egargo.spring_docker.dto.UserCheckDTO;
import io.egargo.spring_docker.dto.UserCreateDTO;
import io.egargo.spring_docker.dto.UserDTO;
import io.egargo.spring_docker.model.User;

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
	Page<UserDTO> getAllUsers(Pageable pageable);

	@Query("SELECT " +
			"new io.egargo.spring_docker.dto.UserDTO(" +
			"	u.id, u.firstName, u.middleName, u.lastName, u.userName, u.email" +
			") " +
			"FROM User u " +
			"WHERE u.id = :id")
	Optional<UserDTO> getUserById(@Param("id") Long id);

	@Query("SELECT\n" +
			"new io.egargo.spring_docker.dto.UserCheckDTO(\n" +
			"\tu.userName, u.email, u.role, u.password\n" +
			")\n" +
			"FROM User u\n" +
			"WHERE u.userName = :userName")
	Optional<UserCheckDTO> checkUserLogin(@Param("userName") String userName);
}
