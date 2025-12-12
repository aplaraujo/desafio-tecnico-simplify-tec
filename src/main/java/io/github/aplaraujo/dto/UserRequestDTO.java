package io.github.aplaraujo.dto;

import java.util.List;

public record UserRequestDTO(String login, String password, String name, List<String> roles) {
}
