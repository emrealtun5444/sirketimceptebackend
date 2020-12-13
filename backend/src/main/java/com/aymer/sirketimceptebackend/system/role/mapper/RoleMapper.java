package com.aymer.sirketimceptebackend.system.role.mapper;

import com.aymer.sirketimceptebackend.system.role.dto.RoleInput;
import com.aymer.sirketimceptebackend.system.role.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleInput toInput(Role role);
    void updateFromInput(RoleInput roleInput, @MappingTarget Role role);
    List<RoleInput> toInputs(List<Role> roles);
}
