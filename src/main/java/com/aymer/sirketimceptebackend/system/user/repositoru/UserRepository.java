package com.aymer.sirketimceptebackend.system.user.repositoru;

import com.aymer.sirketimceptebackend.system.user.model.User;
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

    @Query("select count(distinct u.id) from User u join u.companies c where u.id = :userId and c.id = :companyId")
    Long countByIdAndCompaniesIn(@Param("userId") Long id, @Param("companyId") Long companyId);
}
