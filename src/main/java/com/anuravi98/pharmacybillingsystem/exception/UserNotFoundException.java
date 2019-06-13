package com.anuravi98.pharmacybillingsystem.exception;

public class UserNotFoundException extends Exception {
    private String userName;

    public UserNotFoundException(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "user" + "not found";
    }
}
