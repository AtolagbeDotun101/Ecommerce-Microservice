package com.api.order.controller;

import com.api.order.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService orderLineService;


}
