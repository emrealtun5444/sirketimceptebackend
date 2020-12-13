package com.aymer.sirketimceptebackend.system.role.api;


import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.system.role.dto.RoleInput;
import com.aymer.sirketimceptebackend.system.role.model.Authorization;
import com.aymer.sirketimceptebackend.system.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/system/roles")
@Slf4j
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    List<RoleInput> list() {
        return service.listRoles();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    RoleInput add(@RequestBody RoleInput input) {
        return service.addRole(input);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    RoleInput update(@PathVariable Long id, @RequestBody RoleInput input) {
        return service.updateRole(id, input);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    RoleInput get(@PathVariable Long id) {
        return service.getRole(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    void delete(@PathVariable Long id) {
        service.deleteRole(id);
    }

    @GetMapping("/authorizations")
    @PreAuthorize("hasAuthority('ROLE_MENU')")
    List<Authorization> getAuthorizations() {
        return service.getAuthorizations();
    }

}
