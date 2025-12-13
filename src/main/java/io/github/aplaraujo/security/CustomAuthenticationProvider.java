package io.github.aplaraujo.security;

import io.github.aplaraujo.entities.User;
import io.github.aplaraujo.services.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserService service;
    private final PasswordEncoder encoder;

    public CustomAuthenticationProvider(UserService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = service.getUserWithRoles(login);
        boolean passwordsMatch = encoder.matches(password, user.getPassword());
        if(user == null) {
            throw new BadCredentialsException("User not found!");
        }

        if (!passwordsMatch) {
            throw new BadCredentialsException("Password not valid!");
        }

        System.out.println("========== DEBUG AUTHENTICATION ==========");
        System.out.println("Login: " + login);
        System.out.println("Roles do banco:");
        user.getRoles().forEach(role -> System.out.println("  - " + role.getName()));

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList();

        System.out.println("========== DEBUG AUTHENTICATION ==========");
        System.out.println("Login: " + login);
        System.out.println("Roles do banco:");
        user.getRoles().forEach(role -> System.out.println("  - " + role.getName()));

        UserIdentity userIdentity = new UserIdentity(user.getId(), user.getName(), user.getLogin());

        return new UsernamePasswordAuthenticationToken(userIdentity, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
