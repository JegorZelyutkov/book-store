package com.company.storage;

import com.company.entity.User;

import java.util.List;

public interface UserStorage {
    void save(User user);

    void updateName(int id, String name);

    void updatePassword(int id, String password);

    void delete(int id);

    void delete(String login);

    User getById(int id);

    User getByLogin(String login);

    List<User> getAll();

    boolean contains(int id);

    boolean contains(String login);
}
