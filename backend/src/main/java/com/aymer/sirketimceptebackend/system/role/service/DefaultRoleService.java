package com.aymer.sirketimceptebackend.system.role.service;

import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.system.role.dto.RoleInput;
import com.aymer.sirketimceptebackend.system.role.mapper.RoleMapper;
import com.aymer.sirketimceptebackend.system.role.model.Authorization;
import com.aymer.sirketimceptebackend.system.role.model.Role;
import com.aymer.sirketimceptebackend.system.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DefaultRoleService implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Autowired
    public DefaultRoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleInput> listRoles() {
        return roleMapper.toInputs(roleRepository.findAll());
    }

    @Override
    public RoleInput addRole(RoleInput input) {
        Role role = new Role();
        roleMapper.updateFromInput(input, role);
        return roleMapper.toInput(roleRepository.save(role));
    }

    @Override
    public RoleInput updateRole(Long id, RoleInput input) {
        Role role = roleRepository.getOne(id);
        roleMapper.updateFromInput(input, role);
        return roleMapper.toInput(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        //TODO mark as disabled if used?
        roleRepository.deleteById(id);
    }

    @Override
    public RoleInput getRole(Long id) {
        return roleMapper.toInput(roleRepository.findById(id)
                .orElseThrow(() -> new ServiceException("No role entity found with id: " + id)));
    }

    @Override
    public List<Authorization> getAuthorizations() {
        return Arrays.asList(Authorization.values());
    }
}
