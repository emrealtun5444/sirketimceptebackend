package com.aymer.sirketimceptebackend.system.user.service;

import com.aymer.sirketimceptebackend.system.user.repositoru.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean hasAutherationForCompany(Long userId, Long companyId) {
        return userRepository.countByIdAndCompaniesIn(userId, companyId) > 0;
    }
}
