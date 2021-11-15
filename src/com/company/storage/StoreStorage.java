package com.company.storage;

import com.company.entity.Address;
import com.company.entity.Store;

import java.util.List;

public interface StoreStorage {
    void save(Store store);

    void delete(int id);

    void update(int id, String name);

    void update(int id, Address address);

    List<Store> getAll();

    Store getById(int id);

    boolean contains(int id);

    boolean contains(Store store);

}
