package com.company.storage;

import com.company.entity.Address;

import java.util.List;

public interface AddressStorage {
    void save(Address address);

    List<Address> getAll();

    Address getById(int id);

    List<Address> getAllByStreet(String street);

    List<Address> getAllByHome(int home);

    void update(int id, String street);

    void updateHome(int id, String home);

    void delete(int id);

    boolean contains(int id);

    boolean contains(String street);

    boolean contains(Address address);
}
