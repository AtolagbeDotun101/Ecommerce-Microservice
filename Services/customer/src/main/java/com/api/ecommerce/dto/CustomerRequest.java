package com.api.ecommerce.dto;

import com.api.ecommerce.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record CustomerRequest(

      String id,

     @NotNull(message = "firstname cannot be null")
      String firstName,

    @NotNull(message = "lastname cannot be null")
     String lastName,

    @NotNull(message = "email cannot be null")
    @Email(message = "email must be valid")
     String email,

      Address address
){}
