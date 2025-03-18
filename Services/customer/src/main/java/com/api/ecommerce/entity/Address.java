package com.api.ecommerce.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Address {
    private String street;
    private String houseNumber;
    private String zipCode;

    private Address(AddressBuilder builder) {
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.zipCode = builder.zipCode;
    }

    // Static inner Builder class
    public static class AddressBuilder {
        private String street;
        private String houseNumber;
        private String zipCode;

        public AddressBuilder street(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder houseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }

    // Static method to start the builder
    public static AddressBuilder builder() {
        return new AddressBuilder();
    }

    // Getters
    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }
}
