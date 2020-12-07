package com.aymer.sirketimceptebackend.security.mapper;


import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.security.dto.SignupRequest;
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
