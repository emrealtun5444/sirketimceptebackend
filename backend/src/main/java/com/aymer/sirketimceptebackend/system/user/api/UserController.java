package com.aymer.sirketimceptebackend.system.user.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItem;
import com.aymer.sirketimceptebackend.security.service.UserDetailsImpl;
import com.aymer.sirketimceptebackend.system.mail.model.Notification;
import com.aymer.sirketimceptebackend.system.user.dto.UserInput;
import com.aymer.sirketimceptebackend.system.user.dto.UserListItem;
import com.aymer.sirketimceptebackend.system.user.service.UserService;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/user")
public class UserController {

    private final UserService service;
    private final SessionUtils sessionUtils;

    @Autowired
    public UserController(UserService service, SessionUtils sessionUtils) {
        this.service = service;
        this.sessionUtils = sessionUtils;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER_MENU')")
    AppResponse<List<UserListItem>> list() {
        return new AppResponse<List<UserListItem>>(service.listUsers());
    }

    @GetMapping("/grantedUsers")
    @PreAuthorize("hasAuthority('ROOT_AUTHORIZATION')")
    AppResponse<List<UserListItem>> grantedUsers() {
        return new AppResponse<List<UserListItem>>(service.grantedUsers());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER_MENU')")
    AppResponse<UserListItem> add(@RequestBody UserInput input) {
        return new AppResponse<UserListItem>(service.addUser(input));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_MENU')")
    AppResponse<UserListItem> update(@PathVariable Long id, @RequestBody UserInput input) {
        return new AppResponse<UserListItem>(service.updateUser(id, input));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_MENU')")
    AppResponse<UserInput> get(@PathVariable Long id) {
        return new AppResponse<UserInput>(service.getUser(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_MENU')")
    AppResponse delete(@PathVariable Long id) {
        service.deleteUser(id);
        return new AppResponse();
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('USER_MENU')")
    AppResponse<List<SelectItem>> listRoles() {
        return new AppResponse<List<SelectItem>>(service.listRoles());
    }

    @GetMapping("/companies")
    @PreAuthorize("hasAuthority('USER_MENU')")
    AppResponse<List<SelectItem>> listCompanies() {
        return new AppResponse<List<SelectItem>>(service.listCompanies());
    }

    @GetMapping("/notifications")
    @PreAuthorize("hasAuthority('USER_MENU')")
    AppResponse<List<Notification>> listNotifications() {
        return new AppResponse<List<Notification>>(service.listNotifications());
    }

    @PutMapping("/changepassword/{id}")
    @PreAuthorize("hasAuthority('USER_MENU')")
    public AppResponse changePassword(@PathVariable Long id, @RequestBody String newPassword) {
        service.changePassword(id, newPassword);
        return new AppResponse();
    }


}
