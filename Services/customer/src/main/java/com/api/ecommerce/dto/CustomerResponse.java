package com.api.ecommerce.dto;

import com.api.ecommerce.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse (
        String id,
        String firstName,
        String lastName,

        String email,

        Address address
){
}
