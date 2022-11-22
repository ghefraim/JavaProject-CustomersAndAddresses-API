package com.example.payroll;

class AddressNotFoundException extends RuntimeException {

    AddressNotFoundException(Long id) {
        super("Could not find address " + id);
    }
}