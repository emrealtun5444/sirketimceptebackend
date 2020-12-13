package com.aymer.sirketimceptebackend.system.user.service;

import com.aymer.sirketimceptebackend.system.role.model.Role;
import com.aymer.sirketimceptebackend.system.user.dto.UserDto;
import com.aymer.sirketimceptebackend.system.user.mapper.UserMapper;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.system.user.repositoru.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Autowired
    public DefaultUserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.encoder = new BCryptPasswordEncoder();
        this.userMapper = userMapper;
    }

    @Override
    public boolean hasAutherationForCompany(Long userId, Long companyId) {
        return userRepository.countByIdAndCompaniesIn(userId, companyId) > 0;
    }

    @Override
    public UserDto registerUser(User user, String password, Set<Role> roles) {
        user.prepareUserRegister(encoder.encode(password), roles);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public void changePassword(Long userId, String password) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {
            changePassword(user, password);
        });
    }

    private void changePassword(User user, String password) {
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }
}
