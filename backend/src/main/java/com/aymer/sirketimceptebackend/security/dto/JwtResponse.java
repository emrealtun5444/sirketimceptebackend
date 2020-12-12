package com.aymer.sirketimceptebackend.security.dto;

import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
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
    private List<SelectItem> companies;
}
