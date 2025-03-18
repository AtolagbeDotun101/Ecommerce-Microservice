package com.api.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

      Integer id,
      @NotNull(message = "Product name is required")
      String name,
      @NotNull(message = "Product description is required")
      String description,
      @Positive(message = "Price is positive")
      BigDecimal price,
      @Positive(message = "availableQuantity is positive")
      double availableQuantity,
      @NotNull(message = "category_id is required")
      Integer categoryId
){
}
