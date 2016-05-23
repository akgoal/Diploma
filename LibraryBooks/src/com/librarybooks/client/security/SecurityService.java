package com.librarybooks.client.security;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("services/securityService")
public interface SecurityService extends RemoteService {

    Boolean authenticate(String username, String password);

}
