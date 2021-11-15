package com.company.entity;

public class Address {
    private int id;
    private String street;
    private String home;

    public Address() {
    }

    public Address(String street, String home) {
        this.street = street;
        this.home = home;
    }

    public Address(int id, String street, String home) {
        this.id = id;
        this.street = street;
        this.home = home;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
