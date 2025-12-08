package io.github.aplaraujo.mappers;

import io.github.aplaraujo.dto.UserDTO;
import io.github.aplaraujo.entities.User;

public class UserMapper {
    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }
}
