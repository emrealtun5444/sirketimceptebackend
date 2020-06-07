package com.aymer.sirketimceptebackend.controller.user;

import com.aymer.sirketimceptebackend.controller.common.dto.AppResponse;
import com.aymer.sirketimceptebackend.controller.user.dto.UserDto;
import com.aymer.sirketimceptebackend.controller.user.mapper.UserMapper;
import com.aymer.sirketimceptebackend.model.common.Role;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.repository.RoleRepository;
import com.aymer.sirketimceptebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * User: ealtun
 * Date: 12.04.2020
 * Time: 11:00
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserRepository userRepository, UserMapper userMapper,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> userById(@Valid @PathVariable(name = "id") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        UserDto userDto = userMapper.userToUserDto(user.get());
        return ResponseEntity.ok(new AppResponse(userDto));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> allRoles() {
        List<Role> roleList = roleRepository.findAll();
        return ResponseEntity.ok(new AppResponse(userMapper.roleToRoleDtoList(roleList)));
    }

}
