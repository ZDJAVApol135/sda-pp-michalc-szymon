package com.sda.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory == null ? createSession() : sessionFactory;
    }

    private static SessionFactory createSession() {
        Configuration configuration = new Configuration();
        return configuration.buildSessionFactory();
    }
}
