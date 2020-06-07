package com.aymer.sirketimceptebackend.controller.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:02
 */
@Getter
@Setter
public class RoleDto {

    @NotBlank
    @Size(max = 25)
    private String name;

}
