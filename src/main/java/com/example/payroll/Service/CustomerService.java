package com.example.payroll.Service;

import com.example.payroll.Entity.Customer;
import com.example.payroll.Entity.CustomerWithAddress;
import com.example.payroll.ErrorHandler.CustomerNotFoundException;
import com.example.payroll.Repository.AddressRepository;
import com.example.payroll.Repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressService addressService;

    public CustomerService(CustomerRepository customerRepository, AddressService addressService){
        this.customerRepository = customerRepository;
        this.addressService = addressService;
    }

    public List<CustomerWithAddress> getCustomers() {
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

    public CustomerWithAddress getCustomer(Long id) {
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

    public Customer createCustomer(Customer address) {
        return customerRepository.save(address);
    }

    public Customer updateCustomer(Customer newCustomer, Long id) {
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

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
