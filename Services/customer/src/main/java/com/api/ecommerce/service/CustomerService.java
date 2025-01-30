package com.api.ecommerce.service;

import com.api.ecommerce.Repository.CustomerRepo;
import com.api.ecommerce.dto.CustomerRequest;
import com.api.ecommerce.dto.CustomerResponse;
import com.api.ecommerce.entity.Customer;
import com.api.ecommerce.exception.CustomerNotFoundException;
import com.api.ecommerce.utils.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor()
public class CustomerService {


    private final CustomerRepo customerRepository;
    private CustomerMapper mapper;

    public String createCustomer(CustomerRequest request
    ) {
        var customer= customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }


    public void updateCustomer(CustomerRequest request) {

        var customer = customerRepository.findById(request.id()).orElseThrow(()->
                new CustomerNotFoundException("Customer with id:"+
                        request.id() + " not found"));

        mergeCustomer(customer, request);
        customerRepository.save(customer);
    }



    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }if (StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }if (request.address() !=null){
            customer.setAddress(request.address());
        }
    }

    public void findCustomerById(String id) {

    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String id) {
        return customerRepository.findById(id).isPresent();
    }


}
