package com.librarybooks.server.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Bulat on 30.05.16.
 */
public class EmailNotVerifiedException extends AuthenticationException {
    public EmailNotVerifiedException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmailNotVerifiedException(String msg) {
        super(msg);
    }
}
