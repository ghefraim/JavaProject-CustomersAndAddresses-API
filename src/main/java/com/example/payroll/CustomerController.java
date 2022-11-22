package com.example.payroll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

    private final CustomerRepository customerRepository;

    private AddressRepository addressRepository;
    private AddressService addressService;

    CustomerController(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressService = addressService;
        this.addressService = new AddressService(addressRepository);
    }

    @GetMapping("/customers")
    List<CustomerWithAddress> all() {
        List<CustomerWithAddress> customerWithAddressesList = new ArrayList<>();
        var customers = customerRepository.findAll();
        customers.forEach(customer -> {
            var address = addressService.getAddress(customer.getAddressId());

            customerWithAddressesList.add(new CustomerWithAddress(
                    customer.getId(),
                    customer.getName(),
                    address.getId(),
                    address.getCountry(),
                    address.getCity(),
                    address.getStreet(),
                    address.getZip()
                )
            );
        });
        return customerWithAddressesList;
    }

    @PostMapping("/customers")
    Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @GetMapping("/customers/{id}")
    CustomerWithAddress one(@PathVariable Long id) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        var address = addressService.getAddress(customer.getAddressId());

        return new CustomerWithAddress(
            customer.getId(),
            customer.getName(),
            address.getId(),
            address.getCountry(),
            address.getCity(),
            address.getStreet(),
            address.getZip()
        );
    }

    @PutMapping("/customers/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setAddressId(newCustomer.getAddressId());
                    return customerRepository.save(customer);
                })
                .orElseGet(() -> {
                    newCustomer.setId(id);
                    return customerRepository.save(newCustomer);
                });
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}