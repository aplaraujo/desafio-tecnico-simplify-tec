package io.github.aplaraujo.mappers;

import io.github.aplaraujo.dto.RoleDTO;
import io.github.aplaraujo.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setName(dto.name());
        return role;
    }

    public RoleDTO toDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }
}
