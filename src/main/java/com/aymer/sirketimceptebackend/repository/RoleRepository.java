package com.aymer.sirketimceptebackend.repository;


import com.aymer.sirketimceptebackend.model.enums.ERole;
import com.aymer.sirketimceptebackend.model.common.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
