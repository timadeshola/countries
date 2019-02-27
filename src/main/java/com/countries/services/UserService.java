package com.countries.services;

import com.countries.jpa.entity.User;
import com.countries.model.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface UserService {

    User createUser(UserRequest resource);
    User updateUser(UserRequest resource);
    void deleteUser(Long userId);
    Optional<User> viewUserById(Long userId);
    Optional<User> viewUserByUsername(String username);
    Page<User> findAllUsers(Pageable pageable);
    Boolean toggleUserStatus(Long userId);
}
