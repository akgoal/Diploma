package com.librarybooks.server.security.dao;

import com.librarybooks.server.security.data.OrderBook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderBookDaoImpl implements OrderBookDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderBookDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(OrderBook book) {
        sessionFactory.getCurrentSession().save(book);
    }
}
