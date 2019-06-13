package com.anuravi98.pharmacybillingsystem.exception;

public class IncorrectPasswordException extends Exception {
    private String userName;

    public IncorrectPasswordException(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "it is an incorrect password for "+userName;
    }
}
