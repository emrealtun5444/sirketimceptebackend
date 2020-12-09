package com.aymer.sirketimceptebackend.security.service;


import com.aymer.sirketimceptebackend.security.dto.JwtResponse;
import com.aymer.sirketimceptebackend.security.dto.LoginRequest;
import com.aymer.sirketimceptebackend.security.dto.SignupRequest;
import com.aymer.sirketimceptebackend.system.user.model.User;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface AuthService {

    JwtResponse authenticateUser(LoginRequest loginRequest);

    User registerUser(SignupRequest signUpRequest);

}