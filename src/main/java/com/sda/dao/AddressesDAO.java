package com.sda.dao;

import com.sda.db.HibernateUtils;
import com.sda.model.Address;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AddressesDAO {

    public boolean deleteById(Long id) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            Address address = session.find(Address.class, id);
            boolean exists = address != null;
            if (exists) {
                session.remove(address);
            }
            transaction.commit();
            return exists;
        }
    }

    public void create(Address address) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(address);
            transaction.commit();
        }
    }

    public Address update(Address address) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            Address updatedAddress = session.merge(address);
            transaction.commit();
            return updatedAddress;
        }
    }

    public List<Address> findAllByUsername(String username) {
        try (Session session = HibernateUtils.openSession()) {

            String query = "SELECT a FROM Address a WHERE a.user.username = :username";
            return session.createQuery(query, Address.class)
                    .setParameter("username", username)
                    .list();
        }
    }
}