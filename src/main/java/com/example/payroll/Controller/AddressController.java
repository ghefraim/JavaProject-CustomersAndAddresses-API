package com.example.payroll.Controller;

import java.util.List;

import com.example.payroll.Repository.AddressRepository;
import com.example.payroll.Service.AddressService;
import com.example.payroll.Entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {
    @Autowired
    private AddressRepository repository;
    private AddressService service;

    AddressController(AddressRepository repository) {
        this.repository = repository;
        this.service = new AddressService(repository);
    }

    @GetMapping("/addresses")
    List<Address> getAddresses() {
        return service.getAddresses();
    }

    @GetMapping("/addresses/{id}")
    Address getAddress(@PathVariable Long id) {
        return service.getAddress(id);
    }

    @PostMapping("/addresses")
    Address createAddress(@RequestBody Address address) {
        return service.createAddress(address);
    }

    @PutMapping("/addresses/{id}")
    Address updateAddress(@RequestBody Address newAddress, @PathVariable Long id) {
        return service.updateAddress(newAddress, id);
    }

    @DeleteMapping("/addresses/{id}")
    void deleteAddress(@PathVariable Long id) {
        service.deleteAddress(id);
    }
}