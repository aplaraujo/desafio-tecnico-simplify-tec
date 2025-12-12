package io.github.aplaraujo.services;

import io.github.aplaraujo.dto.RoleDTO;
import io.github.aplaraujo.entities.Role;
import io.github.aplaraujo.mappers.RoleMapper;
import io.github.aplaraujo.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public List<RoleDTO> roles() {
        return roleRepository.findAll().stream().map(roleMapper::toDTO).toList();
    }
}
