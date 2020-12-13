package com.aymer.sirketimceptebackend.system.role.service;

import com.aymer.sirketimceptebackend.system.role.dto.RoleInput;
import com.aymer.sirketimceptebackend.system.role.model.Authorization;

import java.util.List;

public interface RoleService {
    List<RoleInput> listRoles();

    RoleInput addRole(RoleInput input);

    RoleInput updateRole(Long id, RoleInput input);

    void deleteRole(Long id);

    RoleInput getRole(Long id);

    List<Authorization> getAuthorizations();
}
