package com.countries.services.impl;

import com.countries.core.exceptions.CustomException;
import com.countries.jpa.entity.Role;
import com.countries.jpa.entity.User;
import com.countries.jpa.repository.RoleRepository;
import com.countries.jpa.repository.UserRepository;
import com.countries.model.response.UserResponse;
import com.countries.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User createUser(UserResponse resource) {
        Optional<User> optionalUser = userRepository.findUserByUsername(resource.getUsername());
        if(optionalUser.isPresent()) {
            throw new CustomException("User already exist with the given title, please choose another username", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setUsername(resource.getUsername());
        user.setPassword(passwordEncoder.encode(resource.getPassword()));
        user.setFirstName(resource.getFirstName());
        user.setLastName(resource.getLastName());
        user.setEmail(resource.getEmail());

        if(resource.getRoleIds() != null) {
            List<Role> roleList = roleRepository.findAllById(resource.getRoleIds());
            Set<Role> roles = new HashSet<>(roleList);
            user.setRoles(roles);
        }
        user.setStatus(true);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserResponse resource) {
        Optional<User> optionalUser = userRepository.findById(resource.getId());
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstName(resource.getFirstName());
            user.setLastName(resource.getLastName());
            user.setEmail(resource.getEmail());

            if(resource.getRoleIds() != null) {
                List<Role> roleList = roleRepository.findAllById(resource.getRoleIds());
                Set<Role> roles = new HashSet<>(roleList);
                user.setRoles(roles);
            }
            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Optional<User> viewUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> viewUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Boolean toggleUserStatus(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            if(user.getStatus().equals(true)) {
                user.setStatus(false);
            }else {
                user.setStatus(true);
            }
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }
}
