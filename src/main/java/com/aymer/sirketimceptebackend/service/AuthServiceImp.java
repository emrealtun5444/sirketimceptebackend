package com.aymer.sirketimceptebackend.service;

import com.aymer.sirketimceptebackend.controller.common.auth.dto.JwtResponse;
import com.aymer.sirketimceptebackend.controller.common.auth.dto.LoginRequest;
import com.aymer.sirketimceptebackend.controller.common.auth.dto.SignupRequest;
import com.aymer.sirketimceptebackend.controller.common.auth.mapper.AuthMapper;
import com.aymer.sirketimceptebackend.exception.ServiceException;
import com.aymer.sirketimceptebackend.model.common.Role;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.ERole;
import com.aymer.sirketimceptebackend.repository.RoleRepository;
import com.aymer.sirketimceptebackend.repository.UserRepository;
import com.aymer.sirketimceptebackend.security.jwt.JwtUtils;
import com.aymer.sirketimceptebackend.security.service.UserDetailsImpl;
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


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


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
        return new JwtResponse(userDetails.getName(), userDetails.getSurname(), jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User registerUser(SignupRequest signUpRequest) {
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        // Create new user's account
        User user = AuthMapper.INSTANCE.signupRequestToUser(signUpRequest);
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setRoles(roles);
        user.setDurum(EDurum.AKTIF);
        return userRepository.save(user);
    }

}
