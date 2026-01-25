package com.sajidtech.easytrip.controller;

import com.sajidtech.easytrip.dto.response.ApiResponse;
import com.sajidtech.easytrip.enums.Gender;
import com.sajidtech.easytrip.dto.request.CustomerRequest;
import com.sajidtech.easytrip.dto.response.BookingResponse;
import com.sajidtech.easytrip.dto.response.CustomerResponse;
import com.sajidtech.easytrip.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    // Service layer dependency
    @Autowired
    private CustomerService customerService;

    // Register new customer
    @PostMapping("/register/customer")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.createCustomer(customerRequest);
        return ResponseEntity.ok(ApiResponse.success("Customer Registered", customerResponse));
    }

    // Get customer by ID
    @GetMapping("/customer")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@RequestParam("id") int customerId){
        CustomerResponse customerResponse = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(ApiResponse.success("Customer found", customerResponse));
    }

    // Search by gender and age
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllByGenderAndAge(@RequestParam("gender") Gender gender,
                                                       @RequestParam("age") int age){
        List<CustomerResponse> responses = customerService.getAllByGenderAndAge(gender, age);
        return ResponseEntity.ok(ApiResponse.success("Customers fetched", responses));
    }

    // Get customers above age
    @GetMapping("/search/greater")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllGreaterThenAge(@RequestParam("age") int age){
        List<CustomerResponse> responses = customerService.getAllGreaterThenAge(age);
        return ResponseEntity.ok(ApiResponse.success("Filtered customers", responses));
    }

    // Update customer details
    @PutMapping("/customer/{id}/update")
    public ResponseEntity<ApiResponse<String>> updateCustomerInfo(@RequestBody CustomerRequest customerRequest,
                                     @PathVariable("id") int customerId){
        customerService.updateCustomerInfo(customerRequest, customerId);
        return ResponseEntity.ok(ApiResponse.success("Customer updated"));
    }

    // Get all bookings of customer
    @GetMapping("/customer/{id}/bookings")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings(@PathVariable("id") int customerId){
        List<BookingResponse> responses = customerService.getAllBookings(customerId);
        return ResponseEntity.ok(ApiResponse.success("Bookings fetched", responses));
    }

    // Get completed bookings
    @GetMapping("/customer/{id}/bookings/completed")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllCompletedBookings(@PathVariable("id") int customerId){
        List<BookingResponse> responses = customerService.getAllCompletedBookings(customerId);
        return ResponseEntity.ok(ApiResponse.success("Completed bookings", responses));
    }

    // Get cancelled bookings
    @GetMapping("/customer/{id}/bookings/cancelled")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllCancelledBookings(@PathVariable("id") int customerId){
        List<BookingResponse> responses = customerService.getAllCancelledBookings(customerId);
        return ResponseEntity.ok(ApiResponse.success("Cancelled bookings", responses));
    }

    // Get in-progress booking
    @GetMapping("/customer/{id}/bookings/in-progress")
    public ResponseEntity<ApiResponse<BookingResponse>> getProgressBookings(@PathVariable("id") int customerId){
        BookingResponse response = customerService.getProgressBookings(customerId);
        return ResponseEntity.ok(ApiResponse.success("Active booking", response));
    }

    //In Active customer
    @DeleteMapping("/customer/{id}/delete")
    public ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable("id") int customerId){
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok(ApiResponse.success("Customer INACTIVE right now!"));
    }
}
