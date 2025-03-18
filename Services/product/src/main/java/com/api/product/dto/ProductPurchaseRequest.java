package com.api.product.dto;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer id,
        @NotNull(message = "quantity is mandatory")
        double quantity
) {
}
