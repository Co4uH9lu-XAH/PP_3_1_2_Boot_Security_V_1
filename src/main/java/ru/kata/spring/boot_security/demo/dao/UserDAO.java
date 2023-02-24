package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    void updateUser(int id, User user);
    void removeUser(int id);
    List<User> getAllUsers();
    User getUser (int id);
}
