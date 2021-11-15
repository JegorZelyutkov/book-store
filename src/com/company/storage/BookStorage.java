package com.company.storage;

import com.company.entity.Author;
import com.company.entity.Book;

import java.util.List;

public interface BookStorage {
    void save(Book book);

    void delete(int id);

    void delete(Book book);

    void updateTitle(int id, String title);

    void updateDescription(int id, String description);

    void updateAuthor(int id, Author author);

    boolean exist(int id);

    boolean exist(String title);

    List<Book> getAll();

    Book getById(int id);

    Book getByTitle(String title);
}
