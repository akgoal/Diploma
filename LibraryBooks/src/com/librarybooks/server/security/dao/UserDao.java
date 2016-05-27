package com.librarybooks.server.security.dao;

import com.librarybooks.server.security.data.User;

public interface UserDao {

    User findUserByUsername(String username);
}
