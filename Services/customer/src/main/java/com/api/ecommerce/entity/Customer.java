package com.api.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document
public class Customer {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private Address address;

    private Customer(CustomerBuilder builder) {
        this.id = builder.id;
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.address = builder.address;
    }

    public static class CustomerBuilder {
        private String id;
        private String firstname;
        private String lastname;
        private String email;
        private Address address;

        public CustomerBuilder id(String id) {
            this.id = id;
            return this;
        }

        public CustomerBuilder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public CustomerBuilder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder address(Address address) {
            this.address = address;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    // Static method to start the builder
    public static CustomerBuilder builder() {
        return new CustomerBuilder();
    }

    // Getters and Setters (if needed)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}