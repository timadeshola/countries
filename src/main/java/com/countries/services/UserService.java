package com.countries.services;

import com.countries.jpa.entity.User;
import com.countries.model.request.UpdateUserRequest;
import com.countries.model.request.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface UserService {

    User createUser(UserRequest request);
    User updateUser(UpdateUserRequest request);
    void deleteUser(Long userId);
    Optional<User> viewUserByUsername(String username);
    Page<User> findAllUsers(Pageable pageable);
    Boolean toggleUserStatus(Long userId);
}
