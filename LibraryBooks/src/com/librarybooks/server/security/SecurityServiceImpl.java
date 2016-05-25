package com.librarybooks.server.security;

import com.librarybooks.client.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Boolean authenticate(String username, String password) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        Authentication authentication = new UsernamePasswordAuthenticationToken(new User(username, password, authorityList), null, authorityList);
        authenticationManager.authenticate(authentication);
        return true;
    }
}
