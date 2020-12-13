package com.aymer.sirketimceptebackend.system.role.dto;

import com.aymer.sirketimceptebackend.system.role.model.Authorization;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleInput implements Serializable {
    private Long id;
    private String name;
    private List<Authorization> authorizations;
}
