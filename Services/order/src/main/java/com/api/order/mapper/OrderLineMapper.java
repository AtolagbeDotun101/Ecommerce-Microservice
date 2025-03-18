package com.api.order.mapper;

import com.api.order.dto.OrderLineRequest;
import com.api.order.entity.Order;
import com.api.order.entity.OrderLine;

public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request){
        return  OrderLine.builder()
                .id(request.id())
                .productId(request.productId())
                .order(Order.builder()
                        .id(request.id())
                        .build())
                .quantity(request.quantity())
                .build();
    }
}
