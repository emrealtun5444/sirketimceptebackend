package com.aymer.sirketimceptebackend.system.user.service;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItem;
import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.role.model.Role;
import com.aymer.sirketimceptebackend.system.user.dto.UserInput;
import com.aymer.sirketimceptebackend.system.user.dto.UserListItem;
import com.aymer.sirketimceptebackend.system.user.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserListItem> listUsers();

    List<UserListItem> grantedUsers();

    UserListItem addUser(UserInput input);

    UserListItem updateUser(Long id, UserInput input);

    void deleteUser(Long id);

    UserInput getUser(Long id);

    List<SelectItem> listRoles();

    List<SelectItem> listCompanies();

    List<Notification> listNotifications();

    UserInput registerUser(User user, String password, Set<Role> roles);

    void changePassword(Long userId, String password);

    boolean hasAutherationForCompany(Long userId, Long companyId);
}
