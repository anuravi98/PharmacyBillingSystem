package com.anuravi98.pharmacybillingsystem.exception;

public class UserExistsException extends Exception {
    private String userName;

    public UserExistsException(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "this user "+ userName + "already exixts";
    }
}
