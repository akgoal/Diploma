package com.librarybooks.server.security.dao;

import com.librarybooks.server.security.data.EmailVerify;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class EmailVerifyDaoImpl implements EmailVerifyDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public EmailVerifyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(EmailVerify emailVerify) {
        sessionFactory.getCurrentSession().save(emailVerify);
    }

    @Override
    public EmailVerify getByToken(String token) {
        return (EmailVerify) sessionFactory.getCurrentSession().createCriteria(EmailVerify.class).add(Restrictions.eq("token", token)).uniqueResult();
    }
}
