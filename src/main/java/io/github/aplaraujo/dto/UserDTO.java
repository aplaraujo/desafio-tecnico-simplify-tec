package io.github.aplaraujo.dto;


import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserDTO(
        @NotNull(message = "Required field")
        String name,
        @NotNull(message = "Required field")
        String email,
        @NotNull(message = "Required field")
        String password,
        List<String> roles
) {
}
