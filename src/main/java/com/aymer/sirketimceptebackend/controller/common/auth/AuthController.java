package com.aymer.sirketimceptebackend.controller.common.auth;

import com.aymer.sirketimceptebackend.controller.common.auth.dto.JwtResponse;
import com.aymer.sirketimceptebackend.controller.common.auth.dto.LoginRequest;
import com.aymer.sirketimceptebackend.controller.common.auth.dto.SignupRequest;
import com.aymer.sirketimceptebackend.controller.common.auth.mapper.AuthMapper;
import com.aymer.sirketimceptebackend.controller.common.dto.AppResponse;
import com.aymer.sirketimceptebackend.exception.ServiceException;
import com.aymer.sirketimceptebackend.model.common.Role;
import com.aymer.sirketimceptebackend.model.common.User;
import com.aymer.sirketimceptebackend.model.enums.EDurum;
import com.aymer.sirketimceptebackend.model.enums.ERole;
import com.aymer.sirketimceptebackend.repository.UserRepository;
import com.aymer.sirketimceptebackend.service.AuthService;
import com.aymer.sirketimceptebackend.utils.LocaleAwareMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private LocaleAwareMessageProvider messageProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(new AppResponse(jwtResponse));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new ServiceException("error.user.name.already.taken");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new ServiceException("error.email.already.taken");
        }

        authService.registerUser(signUpRequest);

        return ResponseEntity.ok(new AppResponse(HttpStatus.OK.value(), messageProvider.getMessage("success.register")));
    }
}
