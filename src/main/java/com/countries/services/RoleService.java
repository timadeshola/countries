package com.countries.services;

import com.countries.jpa.entity.Role;
import com.countries.model.request.RoleRequest;

import java.util.Optional;

public interface RoleService {

    Role createRole(RoleRequest resource);

    Role updateRole(RoleRequest resource);

    void deleteRole(Long roleId);

    Optional<Role> viewRoleById(Long roleId);

    Optional<Role> viewRoleByName(String name);

    Boolean toggleRoleStatus(Long roleId);
}
