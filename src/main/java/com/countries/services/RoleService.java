package com.countries.services;

import com.countries.jpa.entity.Role;
import com.countries.model.response.RoleResponse;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RoleService {

    Role createRole(RoleResponse resource);

    Role updateRole(RoleResponse resource);

    void deleteRole(Long roleId);

    Page<Role> viewAllRoles(Predicate predicate, Pageable pageable);

    Optional<Role> viewRoleById(Long roleId);

    Optional<Role> viewRoleByName(String name);

    Boolean toggleRoleStatus(Long roleId);
}
