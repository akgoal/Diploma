package com.librarybooks.server.security;

import com.librarybooks.server.security.dao.UserDao;
import com.librarybooks.server.security.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProviderImpl implements AuthenticationProvider {

    public static final String USER_AUTHORITY = "USER";

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user = userDao.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        String storedPass = user.getPassword();
        if (passwordEncoder.matches(storedPass, passwordEncoder.encode(password)))
            throw new BadCredentialsException("Invalid password");

        if (!user.getEmailVerified()) {
            throw new EmailNotVerifiedException("Email not verified");
        }

        CustomUserAuthentication customUserAuthentication = new CustomUserAuthentication(USER_AUTHORITY, authentication);
        customUserAuthentication.setAuthenticated(true);
        return customUserAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
