package com.example.payroll;

import com.example.payroll.Entity.Address;
import com.example.payroll.Entity.Customer;
import com.example.payroll.Repository.AddressRepository;
import com.example.payroll.Repository.CustomerRepository;
import com.example.payroll.Service.AddressService;
import com.example.payroll.Service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, AddressRepository addressRepository) {
        AddressService addressService = new AddressService(addressRepository);
        CustomerService customerService = new CustomerService(customerRepository, addressService);
        return args -> {
            log.info("Preloading " + addressService.createAddress(new Address("Romania", "Oradea", "Blvd. Decebal 12A", "412341")));
            log.info("Preloading " + addressService.createAddress(new Address("Romania", "Arad", "Ciresilor 12A", "400341")));
            log.info("Preloading " + customerService.createCustomer(new Customer("Ghiurau Efraim", (long)1)));
            log.info("Preloading " + customerService.createCustomer(new Customer("Popescu Ion", (long)2)));
        };
    }
}