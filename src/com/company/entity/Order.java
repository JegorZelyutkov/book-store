package com.company.entity;

import java.util.Arrays;

public class Order {
    private int id;
    private Store store;
    private Address address;
    private Book[] books;
    private Type type;
    private User user;

    public Order(int id, Store store, Book[] books, User user) {
        this.id = id;
        this.store = store;
        this.books = books;
        this.user = user;
    }

    public Order(int id, Address address, Book[] books, User user) {
        this.id = id;
        this.address = address;
        this.books = books;
        this.user = user;
    }

    public Order(Store store, Book[] books, User user) {
        this.store = store;
        this.books = books;
        this.user = user;
        this.type = Type.PICKUP;
    }

    public Order(Address address, Book[] books, User user) {
        this.address = address;
        this.books = books;
        this.user = user;
        this.type = Type.DELIVERY;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", store=" + store +
                ", address=" + address +
                ", books=" + Arrays.toString(books) +
                ", type=" + type +
                ", user=" + user +
                '}';
    }
}
