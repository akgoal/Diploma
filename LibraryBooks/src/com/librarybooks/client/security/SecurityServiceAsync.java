package com.librarybooks.client.security;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.librarybooks.shared.security.EmailAlreadyExistsException;
import com.librarybooks.shared.security.UserDto;
import com.librarybooks.shared.security.UserEmailNotVerifiedException;
import com.librarybooks.shared.security.UsernameAlreadyExistsException;

public interface SecurityServiceAsync {

    void authenticate(String username, String password, AsyncCallback<Boolean> asyncCallback);

    void register(UserDto userDto, AsyncCallback<Void> async);
}
