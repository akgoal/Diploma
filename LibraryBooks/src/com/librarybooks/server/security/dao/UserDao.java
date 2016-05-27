package com.librarybooks.server.security.dao;

import com.librarybooks.server.security.data.UserDataSet;

public interface UserDao {

    UserDataSet findUserByUsername(String username);
}
