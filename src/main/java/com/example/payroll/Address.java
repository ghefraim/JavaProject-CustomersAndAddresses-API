package com.example.payroll;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String zip;
    private String country;

    Address() {}

    Address(String country, String city, String street, String zip) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

    //getters
    public Long getId() {
        return this.id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public String getZip() {
        return zip;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Address))
            return false;
        Address Address = (Address) o;
        return Objects.equals(this.id, Address.id)
                && Objects.equals(this.country, Address.country)
                && Objects.equals(this.city, Address.city)
                && Objects.equals(this.street, Address.street)
                && Objects.equals(this.zip, Address.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.country, this.city, this.street, this.zip);
    }

    @Override
    public String toString() {
        return "Address{" + "id=" + this.id +
                ", country='" + this.country + '\'' +
                ", city='" + this.city + '\'' +
                ", street='" + this.street + '\'' +
                ", zip='" + this.zip + '\'' + '}';
    }
}