package com.api.ecommerce.controller;

import com.api.ecommerce.dto.CustomerRequest;
import com.api.ecommerce.dto.CustomerResponse;
import com.api.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request) {
        customerService.updateCustomer(request);
        return ResponseEntity.ok("Customer updated");
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
       return ResponseEntity.ok(customerService.findAllCustomers());

    }

    @GetMapping("/exist/{id}")
    public  ResponseEntity<Boolean> existById(@PathVariable String id){
        return ResponseEntity.ok(customerService.existById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable String id) {
        customerService.findCustomerById(id);
        return ResponseEntity.status(HttpStatus.OK).body("");
    }



}
