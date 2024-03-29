package com.aymer.sirketimceptebackend.system.role.dto;

import com.aymer.sirketimceptebackend.system.role.model.Authorization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleInput implements Serializable {
    private Long id;
    private String name;
    private List<Authorization> authorizations;
}
