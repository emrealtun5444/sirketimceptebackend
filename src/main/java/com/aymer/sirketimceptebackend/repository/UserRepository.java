package com.aymer.sirketimceptebackend.repository;

import com.aymer.sirketimceptebackend.model.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query("select u from User u join fetch u.roles where u.id = :userId")
	Optional<User> getUserWithRoles(@Param("userId") Long userId);
}
