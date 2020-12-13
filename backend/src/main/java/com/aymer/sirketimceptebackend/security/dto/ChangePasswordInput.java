package com.aymer.sirketimceptebackend.security.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordInput {
    @NotNull
    private String existPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String repeatPassword;
}
