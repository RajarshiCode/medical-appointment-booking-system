package com.cj.entity;

public class Patient extends User {
    public Patient(String id, String name) {
        super(id, name);
    }

    @Override
    public void showProfile() {
        System.out.println("Patient ID: " + id + ", Name: " + name);
    }
}

