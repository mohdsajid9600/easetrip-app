package com.sajidtech.easytrip.controller;

import com.sajidtech.easytrip.dto.request.BookingRequest;
import com.sajidtech.easytrip.dto.response.ApiResponse;
import com.sajidtech.easytrip.dto.response.BookingResponse;
import com.sajidtech.easytrip.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    // Booking service dependency
    @Autowired
    private BookingService bookingService;

    // Book a cab for customer
    @PostMapping("/customer/{id}/booked")
    public ResponseEntity<ApiResponse<BookingResponse>> bookCab(@RequestBody BookingRequest bookingRequest,
                                               @PathVariable("id") int customerId){
        BookingResponse bookingResponse = bookingService.bookCab(bookingRequest, customerId);
        return ResponseEntity.ok(ApiResponse.success("Booking created", bookingResponse));
    }

    // Update booked cab details
    @PutMapping("/customer/{id}/update")
    public ResponseEntity<ApiResponse<BookingResponse>> updateBookedDetails(@RequestBody BookingRequest bookingRequest,
                                                               @PathVariable("id") int customerId){
        BookingResponse bookingResponse = bookingService.updateBookedDetails(bookingRequest, customerId);
        return ResponseEntity.ok(ApiResponse.success("Booking updated",bookingResponse));
    }

    // Cancel customer booking
    @PutMapping("/customer/{id}/cancel")
    public ResponseEntity<ApiResponse<String>> cancelBooking(@PathVariable("id") int customerId){
         bookingService.cancelBooking(customerId);
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled"));
    }

    // Complete booking by driver
    @PutMapping("/driver/{id}/complete")
    public ResponseEntity<ApiResponse<String>> completeBookingByDriver(@PathVariable("id") int driverId){
        bookingService.completeBookingByDriver(driverId);
        return ResponseEntity.ok(ApiResponse.success("Booking completed"));
    }


}
