package com.sajidtech.easytrip.controller;

import com.sajidtech.easytrip.dto.response.ApiResponse;
import com.sajidtech.easytrip.dto.request.CustomerRequest;
import com.sajidtech.easytrip.dto.response.BookingResponse;
import com.sajidtech.easytrip.dto.response.CustomerResponse;
import com.sajidtech.easytrip.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    // Service layer dependency
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // Register new customer
    @PostMapping("/create-profile/customer")
    public ResponseEntity<ApiResponse<CustomerResponse>> createProfile(@Valid @RequestBody CustomerRequest customerRequest, Principal principal){
        CustomerResponse customerResponse = this.customerService.createProfile(customerRequest, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Customer Registered", customerResponse));
    }

    // Get customer self info
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerInfo(Principal principal){
        CustomerResponse customerResponse = this.customerService.getCustomerInfo(principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Customer found", customerResponse));
    }

    // Update customer details
    @PutMapping("/update/me")
    public ResponseEntity<ApiResponse<String>> updateCustomerInfo(@Valid @RequestBody CustomerRequest customerRequest,
                                     Principal principal){
        this.customerService.updateCustomerInfo(customerRequest, principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Customer updated"));
    }

    // Get all bookings of customer
    @GetMapping("/customer/bookings")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings(Principal principal){
        List<BookingResponse> responses = this.customerService.getAllBookings(principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Bookings fetched", responses));
    }

    // Get completed bookings
    @GetMapping("/customer/bookings/completed")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllCompletedBookings(Principal principal){
        List<BookingResponse> responses = this.customerService.getAllCompletedBookings(principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Completed bookings", responses));
    }

    // Get cancelled bookings
    @GetMapping("/customer/bookings/cancelled")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllCancelledBookings(Principal principal){
        List<BookingResponse> responses = this.customerService.getAllCancelledBookings(principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Cancelled bookings", responses));
    }

    // Get in-progress booking
    @GetMapping("/customer/bookings/in-progress")
    public ResponseEntity<ApiResponse<BookingResponse>> getProgressBookings(Principal principal){
        BookingResponse response = this.customerService.getProgressBookings(principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Active booking", response));
    }

    //In Active customer
    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<String>> deactivateProfile(Principal principal){
        this.customerService.deactivateProfile(principal.getName());
        return ResponseEntity.ok(ApiResponse.success("Customer INACTIVE right now!"));
    }
}
