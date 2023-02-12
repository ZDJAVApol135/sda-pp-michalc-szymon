package com.sda.dao;

import com.sda.db.HibernateUtils;
import com.sda.model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UsersDAOTest {

    private final UsersDAO usersDAO = new UsersDAO();

    @Test
    @DisplayName("This method tests if user was created properly.")
    void testCreateHappyPath() {
        // given
        String username = UUID.randomUUID().toString();

        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setAge(50);
        expectedUser.setName("John");
        expectedUser.setPassword("pass");
        expectedUser.setSurname("Wayne");
        expectedUser.setEmail("jw@gmail.com");
        // when
        usersDAO.create(expectedUser);
        // then
        Session session = HibernateUtils.openSession();
        User actualUser = session.find(User.class, username);
        session.close();

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        Assertions.assertEquals(expectedUser.getAge(), actualUser.getAge());
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expectedUser.getSurname(), actualUser.getSurname());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());

    }
}
