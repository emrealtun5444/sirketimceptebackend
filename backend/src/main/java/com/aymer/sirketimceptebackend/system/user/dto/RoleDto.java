package com.aymer.sirketimceptebackend.system.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:02
 */
@Getter
@Setter
public class RoleDto {

    @NotNull
    @Size(max = 25)
    private String name;

}
