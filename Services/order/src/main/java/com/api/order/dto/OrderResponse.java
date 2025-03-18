package com.api.order.dto;

import com.api.order.Enum.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {}

