package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDao {
    List<User> index();
    User showUser(int id);
    void saveUser(User user);

    void updateUser(int id, User user);

    void deleteUser(int id);
}
