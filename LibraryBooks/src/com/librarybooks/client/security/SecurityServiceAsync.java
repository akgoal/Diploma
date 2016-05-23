package com.librarybooks.client.security;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SecurityServiceAsync {

    void authenticate(String username, String password, AsyncCallback<Boolean> asyncCallback);

}
