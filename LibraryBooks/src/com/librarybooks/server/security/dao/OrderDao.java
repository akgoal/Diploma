package com.librarybooks.server.security.dao;

import com.librarybooks.server.security.data.EmailVerify;
import com.librarybooks.server.security.data.Order;

import java.util.List;

public interface OrderDao {

    void save(Order order);

    List<Order> getByUserId(Long id);
}
