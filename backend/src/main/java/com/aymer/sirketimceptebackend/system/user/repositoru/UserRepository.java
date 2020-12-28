package com.aymer.sirketimceptebackend.system.user.repositoru;

import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.sirket.model.Sirket;
import com.aymer.sirketimceptebackend.system.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.code like %:code%")
	Optional<User> findByCode(String code);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Query("select distinct u from User u join u.companies c where c = :company")
	List<User> findUserListByCompany(@Param("company") Sirket sirket);

	@Query("select u from User u left join fetch u.roles left join fetch u.companies where u.id = :userId")
	Optional<User> getFetchedUser(@Param("userId") Long userId);

    @Query("select count(distinct u.id) from User u join u.companies c where u.id = :userId and c.id = :companyId")
    Long countByIdAndCompaniesIn(@Param("userId") Long id, @Param("companyId") Long companyId);
}
