package com.sajidtech.easytrip.service;

import com.sajidtech.easytrip.dto.request.CustomerRequest;
import com.sajidtech.easytrip.dto.response.CustomerResponse;
import com.sajidtech.easytrip.exception.CustomerNotFoundException;
import com.sajidtech.easytrip.model.Customer;
import com.sajidtech.easytrip.repository.CustomerRepository;
import com.sajidtech.easytrip.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerTransformer.customerRequestToCustomer(customerRequest);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }

    public CustomerResponse getCustomerById(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer Id is Invalid with : "+id));
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}
