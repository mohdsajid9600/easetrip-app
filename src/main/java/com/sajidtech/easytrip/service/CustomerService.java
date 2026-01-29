package com.sajidtech.easytrip.service;

import com.sajidtech.easytrip.dto.request.CustomerRequest;
import com.sajidtech.easytrip.dto.response.BookingResponse;
import com.sajidtech.easytrip.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse createProfile(CustomerRequest customerRequest, String email );

    CustomerResponse getCustomerInfo(String email);

    void updateCustomerInfo(CustomerRequest customerRequest, String email);

    List<BookingResponse> getAllBookings(String email);

    List<BookingResponse> getAllCompletedBookings(String email);

    List<BookingResponse> getAllCancelledBookings(String email);

    BookingResponse getProgressBookings(String email);

    void deactivateProfile(String email);
}
