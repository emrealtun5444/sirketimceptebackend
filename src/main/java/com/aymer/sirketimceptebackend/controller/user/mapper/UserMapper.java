package com.aymer.sirketimceptebackend.controller.user.mapper;

import com.aymer.sirketimceptebackend.controller.user.dto.RoleDto;
import com.aymer.sirketimceptebackend.controller.user.dto.UserDto;
import com.aymer.sirketimceptebackend.model.common.Role;
import com.aymer.sirketimceptebackend.model.common.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:03
 */
@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "roles", source = "roles")
    public abstract UserDto userToUserDto(User user);

    @Mapping(target = "name", source = "name")
    public abstract RoleDto roleToRoleDto(Role role);

    public abstract List<RoleDto> roleToRoleDtoList(List<Role> roleList);

}
