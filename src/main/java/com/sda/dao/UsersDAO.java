package com.sda.dao;

import com.sda.db.HibernateUtils;
import com.sda.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsersDAO {
    public void create(User user) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user);

        transaction.commit();
        session.close();
    }

    public boolean delete(String username) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.find(User.class, username);
        boolean exists = user != null;
        if (exists) {
            session.remove(user);
        }

        transaction.commit();
        session.close();
        return exists;
    }

    public List<User> findAll() {
        Session session = HibernateUtils.openSession();

        String selectQuery = "SELECT u FROM User u";

        List<User> users = session.createQuery(selectQuery, User.class)
                .list();

        session.close();
        return users;
    }

    public User findByUsername(String username) {

        Session session = HibernateUtils.openSession();
        User user = session.find(User.class, username);
        session.close();

        return user;
    }

    public User update(User user) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        User updatedUser = session.merge(user);

        transaction.commit();
        session.close();
        return updatedUser;
    }

    public boolean exists(String username) {
        try (Session session = HibernateUtils.openSession()) {
//            pierwsza opcja:
//            User user = session.find(User.class, username);
//            return user != null;
            String query = "SELECT count(u) FROM User u WHERE u.username = :id";
            Long usersCount = session.createQuery(query, Long.class)
                    .setParameter("id", username)
                    .uniqueResult();
            return usersCount > 0;
        }
    }

}
