package com.countries.services;

import com.countries.jpa.entity.Role;
import com.countries.model.request.RoleRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleService {

    Role createRole(RoleRequest resource);

    Role updateRole(RoleRequest resource);

    void deleteRole(Long roleId);

    Optional<Role> viewRoleById(Long roleId);

    Optional<Role> viewRoleByName(String name);

    Page<Role> findAllRoles(Pageable pageable);

    Boolean toggleRoleStatus(Long roleId);
}
