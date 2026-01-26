package com.sajidtech.easytrip.controller;

import com.sajidtech.easytrip.dto.request.DriverRequest;
import com.sajidtech.easytrip.dto.response.ApiResponse;
import com.sajidtech.easytrip.dto.response.BookingResponse;
import com.sajidtech.easytrip.dto.response.DriverResponse;
import com.sajidtech.easytrip.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    // Driver service dependency
    @Autowired
    private DriverService driverService;

    // Register new driver
    @PostMapping("/register/driver")
    public ResponseEntity<ApiResponse<DriverResponse>> addDriverInfo(@RequestBody DriverRequest driverRequest){
        DriverResponse driverResponse = driverService.addDriverInfo(driverRequest);
        return ResponseEntity.ok(ApiResponse.success("Driver registered", driverResponse));
    }

    // Get driver by ID
    @GetMapping("/driver")
    public ResponseEntity<ApiResponse<DriverResponse>> getDriverById(@RequestParam("id") int id){
        DriverResponse driverResponse = driverService.getDriverById(id);
        return ResponseEntity.ok(ApiResponse.success("Driver found", driverResponse));
    }

    // Update driver details
    @PutMapping("/driver/{id}/update")
    public ResponseEntity<ApiResponse<String>> updateDriverInfo(@RequestBody DriverRequest driverRequest,
                                                   @PathVariable("id") int driverId){
        driverService.updateDriverInfo(driverRequest, driverId);
        return ResponseEntity.ok(ApiResponse.success("Driver updated"));
    }

    // Get all driver bookings
    @GetMapping("/driver/{id}/bookings")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings(@PathVariable("id") int driverId){
        List<BookingResponse> responses = driverService.getAllBookings(driverId);
        return ResponseEntity.ok(ApiResponse.success("Bookings fetched", responses));
    }

    // Get completed bookings
    @GetMapping("/driver/{id}/bookings/completed")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllCompletedBookings(@PathVariable("id") int driverId){
        List<BookingResponse> responses = driverService.getAllCompletedBookings(driverId);
        return ResponseEntity.ok(ApiResponse.success("Completed bookings", responses));
    }

    // Get cancelled bookings
    @GetMapping("/driver/{id}/bookings/cancelled")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllCancelledBookings(@PathVariable("id") int driverId){
        List<BookingResponse> responses = driverService.getAllCancelledBookings(driverId);
        return ResponseEntity.ok(ApiResponse.success("Cancelled bookings", responses));
    }

    // Get in-progress bookings
    @GetMapping("/driver/{id}/bookings/in-progress")
    public ResponseEntity<ApiResponse<BookingResponse>> getAllInProgressBookings(@PathVariable("id") int driverId){
        BookingResponse response = driverService.getAllInProgressBookings(driverId);
        return ResponseEntity.ok(ApiResponse.success("Active bookings",response));
    }

    // Delete driver account
    @DeleteMapping("/driver/{id}")
    public ResponseEntity<ApiResponse<String>> deleteDriverById(@PathVariable("id") int driverId){
        driverService.deleteDriverById(driverId);
        return ResponseEntity.ok(ApiResponse.success("Driver Inactive"));
    }
}
