package com.api.order.service;

import com.api.order.customer.CustomerClient;
import com.api.order.dto.*;
import com.api.order.exception.BusinessException;
import com.api.order.mapper.OrderMapper;
import com.api.order.product.ProductClient;
import com.api.order.repository.OrderRepository;
import com.api.order.kafka.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

     private OrderRepository orderRepo;
     private CustomerClient customerClient;
     private ProductClient productClient;
     private OrderMapper mapper;
     private OrderLineService orderLineService;
     private OrderProducer orderProducer;

     public Integer createOrder(OrderRequest request) {
         var customer = customerClient.findCustomerById(request.customerId())
                 .orElseThrow(()-> new BusinessException("Cannot create order :: customer not found with id:" + request.customerId()));

       var purchasedProducts=  this.productClient.purchaseProducts(request.products());

        var order = this.orderRepo.save(mapper.ToOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

//        Todo payment
         orderProducer.sendOrderConfirmation(
                 new OrderConfirmation(
                         request.reference(),
                         request.amount(),
                         request.paymentMethod(),
                         customer,
                         purchasedProducts
                 )
         );


         return null;
     }

    public List<OrderResponse> getAllOrder() {
        return orderRepo.findAll()
                .stream()
                .map(mapper::fromOrder)
                .toList();
    }


    public OrderResponse getOrderById(Integer orderId) {
         return orderRepo.findById(orderId)
                 .map(mapper::fromOrder)
                 .orElseThrow(()-> new BusinessException("Cannot find order with id:" + orderId))
                 ;
    }
}
