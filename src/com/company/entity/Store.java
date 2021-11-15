package com.company.entity;

public class Store {
    private int id;
    private Address address;
    private String name;

    public Store(int id, Address address, String name) {
        this.id = id;
        this.address = address;
        this.name = name;
    }

    public Store(Address address, String name) {
        this.address = address;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", address=" + address +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
