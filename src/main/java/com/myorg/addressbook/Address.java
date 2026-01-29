package com.myorg.addressbook;

public class Address {
    private String name;
    private String email;
    private String phone;

    public Address(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
    }
}
