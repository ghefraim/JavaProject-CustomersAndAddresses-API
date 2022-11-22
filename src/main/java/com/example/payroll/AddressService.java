package com.example.payroll;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository repository;

    AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    List<Address> getAddresses() {
        return repository.findAll();
    }

    Address getAddress(Long id) {
        return repository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }

    Address createAddress(Address address) {
        return repository.save(address);
    }

    Address updateAddress(Address newAddress, Long id) {
        return repository.findById(id)
                .map(Address -> {
                    Address.setStreet(newAddress.getStreet());
                    Address.setCity(newAddress.getCity());
                    Address.setCountry(newAddress.getCountry());
                    Address.setZip(newAddress.getZip());
                    return repository.save(Address);
                })
                .orElseGet(() -> {
                    newAddress.setId(id);
                    return repository.save(newAddress);
                });
    }

    void deleteAddress(Long id) {
        repository.deleteById(id);
    }
}
