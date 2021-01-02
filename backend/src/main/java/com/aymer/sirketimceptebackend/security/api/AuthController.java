package com.aymer.sirketimceptebackend.security.api;

import com.aymer.sirketimceptebackend.common.api.BaseController;
import com.aymer.sirketimceptebackend.common.api.dto.AppResponse;
import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.security.dto.ChangePasswordInput;
import com.aymer.sirketimceptebackend.security.dto.JwtResponse;
import com.aymer.sirketimceptebackend.security.dto.LoginRequest;
import com.aymer.sirketimceptebackend.security.dto.SignupRequest;
import com.aymer.sirketimceptebackend.security.service.AuthService;
import com.aymer.sirketimceptebackend.system.user.repositoru.UserRepository;
import com.aymer.sirketimceptebackend.utils.LocaleAwareMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = BaseController.BASE_PATH + "/auth")
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

    @PostMapping("/changepassword")
    @PreAuthorize("hasAuthority('ROOT_AUTHORIZATION')")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordInput input) {
        authService.changePassword(input);
        return ResponseEntity.ok(new AppResponse());
    }
}
