package com.shop.models;


public abstract class Person {
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private Address address;

    public Person(String firstName, String lastName, long phoneNumber, String email, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String toString() {
        return "FirstName: " + firstName + "\nLastName: " + lastName +
                "\nPhone Number: " + phoneNumber + "\nEmail: " + email + "\nAddress: { " +
                address.getArea() + ", " + address.getCity() + ", " + address.getState() + " }";
    }
}
