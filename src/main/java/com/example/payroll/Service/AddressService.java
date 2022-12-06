package com.example.payroll.Service;

import com.example.payroll.Entity.Address;
import com.example.payroll.ErrorHandler.AddressNotFoundException;
import com.example.payroll.Repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> getAddresses() {
        return repository.findAll();
    }

    public Address getAddress(Long id) {
        return repository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }

    public Address createAddress(Address address) {
        return repository.save(address);
    }

    public Address updateAddress(Address newAddress, Long id) {
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

    public void deleteAddress(Long id) {
        repository.deleteById(id);
    }
}
