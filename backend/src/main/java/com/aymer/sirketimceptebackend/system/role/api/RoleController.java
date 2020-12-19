package com.aymer.sirketimceptebackend.system.role.api;


import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.system.role.dto.RoleInput;
import com.aymer.sirketimceptebackend.system.role.dto.TreeNode;
import com.aymer.sirketimceptebackend.system.role.model.Authorization;
import com.aymer.sirketimceptebackend.system.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/role")
@Slf4j
public class RoleController {
    private final RoleService service;

    @Autowired
    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    AppResponse<List<RoleInput>> list() {
        return new AppResponse<List<RoleInput>>(service.listRoles());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    AppResponse<RoleInput> add(@RequestBody RoleInput input) {
        return new AppResponse<>(service.addRole(input));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    AppResponse<RoleInput> update(@PathVariable Long id, @RequestBody RoleInput input) {
        return new AppResponse<>(service.updateRole(id, input));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    AppResponse<RoleInput> get(@PathVariable Long id) {
        return new AppResponse<>(service.getRole(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    AppResponse delete(@PathVariable Long id) {
        service.deleteRole(id);
        return new AppResponse();
    }

    @GetMapping("/authorizations")
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    AppResponse<List<TreeNode<String>>> getAuthorizations() {
        return new AppResponse<>(service.getTreeOfAuthorizations());
    }

}
