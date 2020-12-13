package com.aymer.sirketimceptebackend.system.role.repository;


import com.aymer.sirketimceptebackend.system.role.model.ERole;
import com.aymer.sirketimceptebackend.system.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
