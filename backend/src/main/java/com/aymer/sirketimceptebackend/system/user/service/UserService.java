package com.aymer.sirketimceptebackend.system.user.service;

import com.aymer.sirketimceptebackend.system.role.model.Role;
import com.aymer.sirketimceptebackend.system.user.dto.UserDto;
import com.aymer.sirketimceptebackend.system.user.model.User;

import java.util.Set;

public interface UserService {
    boolean hasAutherationForCompany(Long userId, Long companyId);
    void changePassword(Long userId, String password);
    UserDto registerUser(User user, String password, Set<Role> roles);
}
