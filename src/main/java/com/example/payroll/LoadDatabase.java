package com.example.payroll;

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
        AddressService service = new AddressService(addressRepository);
        return args -> {
            log.info("Preloading " + service.createAddress(new Address("Romania", "Oradea", "Blvd. Decebal 12A", "412341")));
            log.info("Preloading " + service.createAddress(new Address("Romania", "Arad", "Ciresilor 12A", "400341")));
            log.info("Preloading " + customerRepository.save(new Customer("Ghiurau Efraim", (long)1)));
            log.info("Preloading " + customerRepository.save(new Customer("Popescu Ion", (long)2)));
        };
    }
}