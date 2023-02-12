package com.sda.dao;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.github.javafaker.Name;
import com.sda.db.HibernateUtils;
import com.sda.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsersDAOTest {

    private final UsersDAO usersDAO = new UsersDAO();

    @Test
    @DisplayName("This method tests if user was created properly.")
    void testCreateHappyPath() {
        // given
        String username = UUID.randomUUID().toString();
        User expectedUser = createUser(username);

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

    @Test
    void deleteUserNotExists() {
        //given
        String usernameNotExisting = "user name not exist";
        //when
        boolean deleted = usersDAO.delete(usernameNotExisting);
        //then
        Assertions.assertFalse(deleted);
    }

    @Test
    public void deleteUserExists() {
        // given
        String username = "username";
        User expectedUser = createUser(username);

        usersDAO.create(expectedUser);

        // when
        boolean deleted = usersDAO.delete(username);

        // then
        Assertions.assertTrue(deleted);
    }

    @Test
    public void findAll() {
        // given
        final List<User> expectedList = List.of(
                createUser(UUID.randomUUID().toString()),
                createUser(UUID.randomUUID().toString()),
                createUser(UUID.randomUUID().toString())
        );

        expectedList.forEach(usersDAO::create);

        // when
        List<User> usersList = usersDAO.findAll();

        // then
        Assertions.assertNotNull(usersList);
        Assertions.assertEquals(expectedList.size(), usersList.size());
        Assertions.assertIterableEquals(expectedList, usersList);
    }

    @Test
    void FindByUsername() {
        // given
        String username = UUID.randomUUID().toString();
        User expectedUser = createUser(username);
        usersDAO.create(expectedUser);

        // when
        User actualUser = usersDAO.findByUsername(username);

        // then
        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);
        Assertions.assertEquals(expectedUser.getAge(), actualUser.getAge());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        Assertions.assertEquals(expectedUser.getSurname(), actualUser.getSurname());
    }

    @Test
    public void updateHappyPath() {
        //given
        String username = UUID.randomUUID().toString();
        User user = createUser(username);
        usersDAO.create(user);

        int updatedAge = 100;
        String updatedName = "updated name";
        user.setName(updatedName);
        user.setAge(updatedAge);

        //when
        User updatedUser = usersDAO.update(user);

        //then
        Assertions.assertNotNull(updatedUser);
        Assertions.assertEquals(updatedAge, updatedUser.getAge());
        Assertions.assertEquals(updatedName, updatedUser.getName());
        Assertions.assertEquals(user.getUsername(), updatedUser.getUsername());
        Assertions.assertEquals(user.getEmail(), updatedUser.getEmail());
        Assertions.assertEquals(user.getPassword(), updatedUser.getPassword());
        Assertions.assertEquals(user.getSurname(), updatedUser.getSurname());
    }

    private User createUser(String username) {
        Faker faker = new Faker();
        Name name = faker.name();
        Internet internet = faker.internet();

        User user = new User();
        user.setUsername(username);
        user.setPassword(internet.password());
        user.setName(name.firstName());
        user.setAge(faker.number().numberBetween(0, 150));
        user.setEmail(internet.emailAddress());
        user.setSurname(name.lastName());

        return user;
    }
}
