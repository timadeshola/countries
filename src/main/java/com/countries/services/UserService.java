package com.countries.services;

import com.countries.jpa.entity.User;
import com.countries.model.response.UserResponse;

import java.util.Optional;

public interface UserService {

    User createUser(UserResponse resource);
    User updateUser(UserResponse resource);
    void deleteUser(Long userId);
    Optional<User> viewUserById(Long userId);
    Optional<User> viewUserByUsername(String username);
    Boolean toggleUserStatus(Long userId);
}
