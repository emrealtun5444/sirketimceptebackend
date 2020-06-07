package com.aymer.sirketimceptebackend.controller.common.auth.mapper;


import com.aymer.sirketimceptebackend.controller.common.auth.dto.SignupRequest;
import com.aymer.sirketimceptebackend.model.common.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * User: ealtun
 * Date: 16.03.2020
 * Time: 16:22
 */
@Mapper
public interface AuthMapper {
    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    User signupRequestToUser(SignupRequest signupRequest);

}
