package com.aymer.sirketimceptebackend.controller.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:02
 */
@Getter
@Setter
public class UserDto {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 128)
    private String name;

    @NotBlank
    @Size(max = 128)
    private String surname;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    private List<RoleDto> roles;
}
