package com.api.order.service;

import com.api.order.dto.OrderLineRequest;
import com.api.order.mapper.OrderLineMapper;
import com.api.order.repository.OrderLineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepo orderLineRepo;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OrderLineRequest request){
        var orderLine = mapper.toOrderLine(request);
        return orderLineRepo.save(orderLine).getId();
    }

}
