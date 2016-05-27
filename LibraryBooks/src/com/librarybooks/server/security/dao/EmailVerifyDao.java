package com.librarybooks.server.security.dao;

import com.librarybooks.server.security.data.EmailVerify;

public interface EmailVerifyDao {

    void save(EmailVerify user);

    EmailVerify getByToken(String token);
}
