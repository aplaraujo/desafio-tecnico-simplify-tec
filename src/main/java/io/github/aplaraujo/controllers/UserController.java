package io.github.aplaraujo.controllers;

import io.github.aplaraujo.dto.UserDTO;
import io.github.aplaraujo.mappers.UserMapper;
import io.github.aplaraujo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody UserDTO dto) {
        var user = userMapper.toEntity(dto);
        userService.save(user);
    }
}
