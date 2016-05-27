package com.librarybooks.server.security.dao;

import com.librarybooks.server.security.data.UserDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserDataSet findUserByUsername(String username) {
        return (UserDataSet) sessionFactory.getCurrentSession().createCriteria(UserDataSet.class).add(Restrictions.eq("username", username)).uniqueResult();
    }
}
