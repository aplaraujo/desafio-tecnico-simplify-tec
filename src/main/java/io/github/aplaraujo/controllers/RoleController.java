package io.github.aplaraujo.controllers;

import io.github.aplaraujo.dto.RoleDTO;
import io.github.aplaraujo.entities.Role;
import io.github.aplaraujo.mappers.RoleMapper;
import io.github.aplaraujo.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController implements GenericController{
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> save(@RequestBody RoleDTO dto) {
        Role role = roleMapper.toEntity(dto);
        roleService.save(role);
        URI location = generateHeaderLocation(role.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleDTO> roles() {
        return roleService.roles();
    }
}
