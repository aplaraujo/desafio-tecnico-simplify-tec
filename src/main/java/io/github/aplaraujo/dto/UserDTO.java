package io.github.aplaraujo.dto;

import java.util.List;

public record UserDTO(
        String name,
        String email,
        String password
) {
}
