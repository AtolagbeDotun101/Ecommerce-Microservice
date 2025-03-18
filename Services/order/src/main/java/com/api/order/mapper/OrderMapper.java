package com.api.order.mapper;

import com.api.order.dto.OrderRequest;
import com.api.order.dto.OrderResponse;
import com.api.order.entity.Order;

public class OrderMapper {

    public Order ToOrder(OrderRequest request){
        return  Order.builder()
                .id(request.id())
                .reference(request.reference())
                .customerId(request.customerId())
                .paymentMethod(request.paymentMethod())
                .totalAmount(request.amount())
                .build();

    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
                );
    }
}
