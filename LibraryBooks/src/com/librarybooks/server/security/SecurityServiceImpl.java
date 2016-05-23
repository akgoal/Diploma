package com.librarybooks.server.security;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.librarybooks.client.security.SecurityService;
import org.springframework.stereotype.Service;

@Service("securityService")
public class SecurityServiceImpl extends RemoteServiceServlet implements SecurityService {

    @Override
    public Boolean authenticate(String username, String password) {
        System.out.print("HELLO WORLD");
        return null;
    }
}
