package io.github.aplaraujo.config;

import io.github.aplaraujo.repositories.RoleRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MasterPasswordAuthenticationProvider implements AuthenticationProvider {
    private final RoleRepository roleRepository;

    public MasterPasswordAuthenticationProvider(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var login = authentication.getName();
        var password = authentication.getCredentials().toString();
        String masterLogin = "master";
        String masterPassword = "master123";

        if(masterLogin.equals(login) && masterPassword.equals(password)) {
            return new UsernamePasswordAuthenticationToken("Master", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
