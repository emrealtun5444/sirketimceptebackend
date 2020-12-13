package com.aymer.sirketimceptebackend.security.service;

import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItem;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItemMapper;
import com.aymer.sirketimceptebackend.common.model.enums.EDurum;
import com.aymer.sirketimceptebackend.security.dto.ChangePasswordInput;
import com.aymer.sirketimceptebackend.security.dto.JwtResponse;
import com.aymer.sirketimceptebackend.security.dto.LoginRequest;
import com.aymer.sirketimceptebackend.security.dto.SignupRequest;
import com.aymer.sirketimceptebackend.security.jwt.JwtUtils;
import com.aymer.sirketimceptebackend.security.mapper.AuthMapper;
import com.aymer.sirketimceptebackend.system.role.model.ERole;
import com.aymer.sirketimceptebackend.system.role.model.Role;
import com.aymer.sirketimceptebackend.system.role.repository.RoleRepository;
import com.aymer.sirketimceptebackend.system.user.dto.UserDto;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.system.user.repositoru.UserRepository;
import com.aymer.sirketimceptebackend.system.user.service.UserService;
import com.aymer.sirketimceptebackend.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
@Service
public class AuthServiceImp implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;
    private SessionUtils sessionUtils;
    private UserService userService;

    @Autowired
    public AuthServiceImp(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, SessionUtils sessionUtils, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.sessionUtils = sessionUtils;
        this.userService = userService;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new ServiceException("error.invalid.username.pasword");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        List<SelectItem> companies = SelectItemMapper.toComboItems(userDetails.getCompanyList());
        return JwtResponse.builder()
            .name(userDetails.getName())
            .surname(userDetails.getSurname())
            .token(jwt).id(userDetails.getId())
            .username(userDetails.getUsername())
            .email(userDetails.getEmail())
            .roles(roles).companies(companies).build();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDto registerUser(SignupRequest signUpRequest) {
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            Role adminRole = roleRepository.findByName(role)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);
        });

        // Create new user's account
        User user = AuthMapper.INSTANCE.signupRequestToUser(signUpRequest);
        return userService.registerUser(user, signUpRequest.getPassword(), roles);
    }

    @Override
    public void changePassword(ChangePasswordInput input) {
        if (!input.getNewPassword().equals(input.getRepeatPassword())) {
            throw new ServiceException("error.new.password.mismatch");
        }
        UserDetailsImpl userDetails = sessionUtils.getUserDetails();
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(), input.getExistPassword()));
        } catch (BadCredentialsException ex) {
            throw new ServiceException("error.exist.password");
        }

        userService.changePassword(userDetails.getId(), input.getNewPassword());
    }
}
