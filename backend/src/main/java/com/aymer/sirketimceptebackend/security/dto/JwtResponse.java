package com.aymer.sirketimceptebackend.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "fk";
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String name, String surname, String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.name = name;
        this.surname = surname;
    }
}
