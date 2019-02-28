package com.countries.services.impl;

import com.countries.core.exceptions.CustomException;
import com.countries.jpa.entity.Role;
import com.countries.jpa.repository.RoleRepository;
import com.countries.model.request.RoleRequest;
import com.countries.model.request.UpdateRoleRequest;
import com.countries.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(RoleRequest resource) {
        Optional<Role> optionalRole = roleRepository.findRoleByName(resource.getName());
        if (optionalRole.isPresent()) {
            throw new CustomException("Role with this title already exist, please choose a different title", HttpStatus.CONFLICT);
        }
        Role role = new Role();
        role.setName(resource.getName().toUpperCase());
        role.setStatus(true);
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(UpdateRoleRequest resource) {
        Optional<Role> optionalRole = roleRepository.findById(resource.getRoleId());
        if (!optionalRole.isPresent()) {
            throw new CustomException("Role not found", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Role role = optionalRole.get();
        role.setName(resource.getName());
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        Optional<Role> roles = roleRepository.findById(roleId);
        roles.ifPresent(role -> roleRepository.delete(role));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> viewRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Role> findAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Boolean toggleRoleStatus(Long roleId) {
        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            if (role.getStatus().equals(true)) {
                role.setStatus(false);
            } else {
                role.setStatus(false);
            }
            roleRepository.saveAndFlush(role);
            return true;
        }
        return false;
    }
}
