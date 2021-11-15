package com.company.storage;

import com.company.entity.*;

import java.util.List;

public interface OrderStorage {
    void save(Order order);

    void delete(int id);

    void updateType(int id, Type type);

    void updateUser(int id, User user);

    void updateAddress(int id, Address address);

    void updateStore(int id, Store store);

    void addBook(int id, Book book);

    void deleteBook(int id, Book book);

    Order getById(int id);

    List<Order> getAllByUser(User user);

    List<Order> getAllByAddress(Address address);

    List<Order> getAllByStore(Store store);

    List<Order> getAllByType(Type type);

    boolean contains(int id);
}
