package com.cj.entity;

public abstract class User {
    protected String id;
    protected String name;

    public User(String id, String name) {
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            throw new IllegalArgumentException("Name must contain only alphabets.");
        }
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public abstract void showProfile();
}

