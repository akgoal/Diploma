package com.librarybooks.server.security.dao;

import com.librarybooks.server.security.data.Order;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getByUserId(Long id) {
        return (List<Order>) sessionFactory.getCurrentSession().createCriteria(Order.class).add(Restrictions.eq("userId", id)).addOrder(org.hibernate.criterion.Order.asc("orderNum")).list();
    }
}
