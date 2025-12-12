package io.github.aplaraujo.services;

import io.github.aplaraujo.dto.UserRequestDTO;
import io.github.aplaraujo.dto.UserResponseDTO;
import io.github.aplaraujo.entities.Role;
import io.github.aplaraujo.entities.User;
import io.github.aplaraujo.mappers.UserMapper;
import io.github.aplaraujo.repositories.RoleRepository;
import io.github.aplaraujo.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponseDTO create(UserRequestDTO request) {
        List<Role> roles = roleRepository.findByNameIn(request.roles());
        User user = userMapper.toEntity(request, roles);
        user.setPassword(passwordEncoder.encode(request.password()));
        user = userRepository.save(user);
        return userMapper.toResponse(user);
    }

    public User getUserWithRoles(String login) {
        Optional<User> userOptional = userRepository.findByLogin(login);
        if (userOptional.isEmpty()) {
            return null;
        }
        User user = userOptional.get();
        return user;
    }
}
