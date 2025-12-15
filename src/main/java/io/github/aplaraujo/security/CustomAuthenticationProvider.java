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

        if(user == null) {
            throw new BadCredentialsException("User not found!");
        }

        boolean passwordsMatch = encoder.matches(password, user.getPassword());

        if (!passwordsMatch) {
            throw new BadCredentialsException("Password not valid!");
        }

        System.out.println("Tentando autenticar: " + login);
        System.out.println("Senha do user: " + user.getPassword());
        System.out.println("Roles: " + user.getRoles());


        List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).toList();
        UserIdentity userIdentity = new UserIdentity(user.getId(), user.getName(), user.getLogin());

        return new UsernamePasswordAuthenticationToken(userIdentity, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
