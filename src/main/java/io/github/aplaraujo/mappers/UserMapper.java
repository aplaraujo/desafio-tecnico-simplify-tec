package io.github.aplaraujo.mappers;

import io.github.aplaraujo.dto.UserRequestDTO;
import io.github.aplaraujo.dto.UserResponseDTO;
import io.github.aplaraujo.entities.Role;
import io.github.aplaraujo.entities.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class UserMapper {
    public UserResponseDTO toResponse(User user) {
        List<String> roles = user.getRoles().stream().map(Role::getName).toList();
        return new UserResponseDTO(user.getId(), user.getLogin(), user.getPassword(), user.getName(), roles);
    }

    public User toEntity(UserRequestDTO dto, List<Role> roles) {
        User user = new User();
        user.setLogin(dto.login());
        user.setPassword(dto.password());
        user.setName(dto.name());
        user.setRoles(new HashSet<>(roles));
        return user;
    }
}
