package com.api.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest (
        @NotNull(message = "Product id is mandatory")
        Integer productId,
        @NotNull(message = "quantity is mandatory")
        @Positive()
        double quantity
) {
}
