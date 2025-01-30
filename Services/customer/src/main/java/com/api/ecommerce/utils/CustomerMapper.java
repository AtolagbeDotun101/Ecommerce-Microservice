package com.api.ecommerce.utils;

import com.api.ecommerce.dto.CustomerRequest;
import com.api.ecommerce.dto.CustomerResponse;
import com.api.ecommerce.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerResponse){
        return Customer.builder()
                .id(customerResponse.id())
                .firstName(customerResponse.firstName())
                .lastName(customerResponse.lastName())
                .email(customerResponse.email())
                .address(customerResponse.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return  new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
