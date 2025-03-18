package com.api.order.controller;

import com.api.order.dto.OrderRequest;
import com.api.order.dto.OrderResponse;
import com.api.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder (@RequestBody OrderRequest request){
        return ResponseEntity.ok(service.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrder(){
        return ResponseEntity.ok(service.getAllOrder());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer orderId){
        return ResponseEntity.ok(service.getOrderById(orderId));
    }

}
