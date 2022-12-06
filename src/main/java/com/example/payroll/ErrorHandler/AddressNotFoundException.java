package com.example.payroll.ErrorHandler;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(Long id) {
        super("Could not find address " + id);
    }
}