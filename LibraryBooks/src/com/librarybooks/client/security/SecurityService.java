package com.librarybooks.client.security;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.librarybooks.shared.security.EmailAlreadyExistsException;
import com.librarybooks.shared.security.UserDto;
import com.librarybooks.shared.security.UserEmailNotVerifiedException;
import com.librarybooks.shared.security.UsernameAlreadyExistsException;

@RemoteServiceRelativePath("services/securityService")
public interface SecurityService extends RemoteService {

    Boolean authenticate(String username, String password) throws UserEmailNotVerifiedException;

    void register(UserDto userDto) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;

}
