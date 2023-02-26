package com.sda.dao;

import com.sda.db.HibernateUtils;
import com.sda.model.SocialMediaLink;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SocialMediaLinkDAO {
    public boolean deleteById(Long id) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            SocialMediaLink socialMediaLink = session.find(SocialMediaLink.class, id);
            boolean exists = socialMediaLink != null;
            if (exists) {
                session.remove(socialMediaLink);
            }
            transaction.commit();
            return exists;
        }
    }

    public void create(SocialMediaLink socialMediaLink) {
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(socialMediaLink);
            transaction.commit();
        }
    }
    public SocialMediaLink update(SocialMediaLink socialMediaLink){
        try (Session session = HibernateUtils.openSession()) {
            Transaction transaction = session.beginTransaction();
            SocialMediaLink updatedSocialMediaLink = session.merge(socialMediaLink);
            transaction.commit();
            return updatedSocialMediaLink;
        }
    }

    public List<SocialMediaLink> findAllByUsername(String username) {
        try (Session session = HibernateUtils.openSession()) {
            String query = "SELECT s FROM SocialMediaLink s WHERE s.user.username = :username";
            return session.createQuery(query, SocialMediaLink.class)
                    .setParameter("username", username)
                    .list();
        }
    }
}
