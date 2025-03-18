package com.api.order.dto;

import java.math.BigDecimal;

public record OrderLineRequest (
        Integer id,
        Integer OrderId,
        Integer productId,
        double quantity
){
}
