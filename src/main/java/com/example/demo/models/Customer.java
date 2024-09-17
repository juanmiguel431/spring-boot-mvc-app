package com.example.demo.models;

import jakarta.validation.constraints.NotEmpty;

public class Customer {

    @NotEmpty(message = "is required")
    private String firstName;

    @NotEmpty(message = "is required")
    private String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Curtomer{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                '}';
    }
}
