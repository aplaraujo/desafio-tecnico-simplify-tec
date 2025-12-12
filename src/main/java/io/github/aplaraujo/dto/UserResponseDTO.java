package io.github.aplaraujo.dto;

import java.util.List;

public record UserResponseDTO(Long id, String login, String password, String name, List<String> roles) {
}
