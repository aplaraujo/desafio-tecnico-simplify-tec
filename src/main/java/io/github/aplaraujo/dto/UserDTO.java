package io.github.aplaraujo.dto;


import jakarta.validation.constraints.NotNull;

public record UserDTO(
        String name,
        @NotNull(message = "Required field")
        String email,
        @NotNull(message = "Required field")
        String password
) {
}
