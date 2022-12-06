package com.example.payroll.Controller;

import java.util.ArrayList;
import java.util.List;

import com.example.payroll.Repository.AddressRepository;
import com.example.payroll.Service.AddressService;
import com.example.payroll.ErrorHandler.CustomerNotFoundException;
import com.example.payroll.Repository.CustomerRepository;
import com.example.payroll.Entity.Customer;
import com.example.payroll.Entity.CustomerWithAddress;
import com.example.payroll.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerRepository customerRepository;

    private AddressRepository addressRepository;
    private AddressService addressService;
    private CustomerService customerService;

    CustomerController(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.addressService = new AddressService(addressRepository);
        this.customerService = new CustomerService(customerRepository, addressService);
    }

    @GetMapping("/customers")
    List<CustomerWithAddress> all() {
        return customerService.getCustomers();
    }

    @PostMapping("/customers")
    Customer createCustomer(@RequestBody Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @GetMapping("/customers/{id}")
    CustomerWithAddress one(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PutMapping("/customers/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return customerService.updateCustomer(newCustomer, id);
    }

    @DeleteMapping("/customers/{id}")
    void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}