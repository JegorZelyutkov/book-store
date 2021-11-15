package com.company.storage;

import com.company.entity.Author;

import java.util.List;

public interface AuthorStorage {
    void save(Author author);

    void delete(int id);

    void delete(Author author);

    void updateName(int id, String name);

    void updateDescription(int id, String description);

    boolean exist(int id);

    boolean exist(String name);

    List<Author> getAll();

    Author getById(int id);

    Author getByName(String name);
}
