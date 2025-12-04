package io.github.aplaraujo.dto;

import io.github.aplaraujo.entities.User;
import io.github.aplaraujo.entities.enums.PriorityType;

public record TodoRequestDTO(
        String name,
        String description,
        Boolean done,
        PriorityType priority,
        User user
) {
}
