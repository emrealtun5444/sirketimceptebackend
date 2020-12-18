package com.aymer.sirketimceptebackend.system.user.service;

import com.aymer.sirketimceptebackend.common.exception.ServiceException;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItem;
import com.aymer.sirketimceptebackend.common.model.abstructcommon.SelectItemMapper;
import com.aymer.sirketimceptebackend.system.role.model.Role;
import com.aymer.sirketimceptebackend.system.role.repository.RoleRepository;
import com.aymer.sirketimceptebackend.system.sirket.repository.SirketRepository;
import com.aymer.sirketimceptebackend.system.user.dto.UserInput;
import com.aymer.sirketimceptebackend.system.user.dto.UserListItem;
import com.aymer.sirketimceptebackend.system.user.mapper.UserMapper;
import com.aymer.sirketimceptebackend.system.user.model.User;
import com.aymer.sirketimceptebackend.system.user.repositoru.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final SirketRepository companyRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public DefaultUserService(UserRepository userRepository, UserMapper userMapper,
                              RoleRepository roleRepository, SirketRepository companyRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean hasAutherationForCompany(Long userId, Long companyId) {
        return userRepository.countByIdAndCompaniesIn(userId, companyId) > 0;
    }

    @Override
    public UserInput registerUser(User user, String password, Set<Role> roles) {
        user.prepareUserRegister(encoder.encode(password), roles);
        return userMapper.toInput(userRepository.save(user));
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


    @Override
    public List<UserListItem> listUsers() {
        return userMapper.toListItems(userRepository.findAll());
    }

    @Override
    public UserListItem addUser(UserInput input) {
        User user = new User();
        userMapper.updateFromInput(input, user);
        String password = encoder.encode(input.getPasswordInput());
        user.setPassword(password);
        return userMapper.toListItem(userRepository.save(user));
    }

    @Override
    public UserListItem updateUser(Long id, UserInput input) {
        User user = userRepository.findById(id).get();
        userMapper.updateFromInput(input, user);
        return userMapper.toListItem(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        //TODO mark as disabled if used?
        userRepository.deleteById(id);
    }

    @Override
    public UserInput getUser(Long id) {
        return userMapper.toInput(userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("No user entity found with id: " + id)));
    }

    @Override
    public List<SelectItem> listRoles() {
        return SelectItemMapper.toComboItems(roleRepository.findAll());
    }

    @Override
    public List<SelectItem> listCompanies() {
        return SelectItemMapper.toComboItems(companyRepository.findAll());
    }
}
