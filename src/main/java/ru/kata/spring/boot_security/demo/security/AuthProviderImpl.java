package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.service.UserSecurityService;

import java.util.Collections;
/*
В этой имплементации в методе в authentication приходит имя и пароль из формы аутентификации,
которые проверяются на соответствие имени и пароля в базе юзеров.
 */

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final UserSecurityService userSecurityService;

    @Autowired
    public AuthProviderImpl(UserSecurityService userSecurityService) {
        this.userSecurityService = userSecurityService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails user = userSecurityService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if (!user.getPassword().equals(password)) {
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(user, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
