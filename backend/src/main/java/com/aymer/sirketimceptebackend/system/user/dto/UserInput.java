package com.aymer.sirketimceptebackend.system.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class UserInput implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String passwordInput;
    private Set<Long> roles;
    private Set<Long> companies;
}
