package com.aymer.sirketimceptebackend.service;


import com.aymer.sirketimceptebackend.controller.common.auth.dto.JwtResponse;
import com.aymer.sirketimceptebackend.controller.common.auth.dto.LoginRequest;
import com.aymer.sirketimceptebackend.controller.common.auth.dto.SignupRequest;
import com.aymer.sirketimceptebackend.model.common.User;

/**
 * User: ealtun
 * Date: 12.03.2020
 * Time: 16:10
 */
public interface AuthService {

    JwtResponse authenticateUser(LoginRequest loginRequest);

    User registerUser(SignupRequest signUpRequest);

}
