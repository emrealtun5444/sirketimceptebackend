package com.aymer.sirketimceptebackend.system.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 128)
    private String name;

    @NotNull
    @Size(max = 128)
    private String surname;

    @NotNull
    @Size(min = 3, max = 20)
    private String username;

    @NotNull
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    private List<String> roles;
}
