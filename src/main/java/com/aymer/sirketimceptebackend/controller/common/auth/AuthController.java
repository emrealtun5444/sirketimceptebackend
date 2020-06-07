package com.aymer.sirketimceptebackend.controller.common.auth;

import com.aymer.sirketimceptebackend.controller.common.auth.dto.JwtResponse;
import com.aymer.sirketimceptebackend.controller.common.auth.dto.LoginRequest;
import com.aymer.sirketimceptebackend.controller.common.auth.dto.SignupRequest;
import com.aymer.sirketimceptebackend.controller.common.auth.mapper.AuthMapper;
import com.aymer.sirketimceptebackend.controller.common.dto.AppResponse;
import com.aymer.sirketimceptebackend.exception.ServiceException;
import com.aymer.sirketimceptebackend.model.enums.ERole;
import com.aymer.sirketimceptebackend.model.common.Role;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.repository.RoleRepository;
import com.aymer.sirketimceptebackend.repository.UserRepository;
import com.aymer.sirketimceptebackend.security.jwt.JwtUtils;
import com.aymer.sirketimceptebackend.security.service.UserDetailsImpl;
import com.aymer.sirketimceptebackend.utils.LocaleAwareMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LocaleAwareMessageProvider messageProvider;

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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

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
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AppResponse(new JwtResponse(userDetails.getName(),
                userDetails.getSurname(),
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ServiceException("error.user.name.already.taken");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ServiceException("error.email.already.taken");
        }

        // Create new user's account
        User user = AuthMapper.INSTANCE.signupRequestToUser(signUpRequest);
        user.setPassword(encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
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

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new AppResponse(HttpStatus.OK.value(), messageProvider.getMessage("success.register")));
    }
}
