package com.sajidtech.easytrip.transformer;

import com.sajidtech.easytrip.dto.request.CustomerRequest;
import com.sajidtech.easytrip.dto.response.CustomerResponse;
import com.sajidtech.easytrip.model.Customer;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .name(customerRequest.getName())
                .age(customerRequest.getAge())
                .email(customerRequest.getEmail())
                .gender(customerRequest.getGender())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .name(customer.getName())
                .age(customer.getAge())
                .email(customer.getEmail())
                .build();
    }
}
