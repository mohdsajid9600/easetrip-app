package com.sajidtech.easytrip.exception;

import com.sajidtech.easytrip.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DriverNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleDriverNotFound(DriverNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(false, ex.getMessage(), "DRIVER_NOT_FOUND", 404),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCustomerNotFound(CustomerNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(false, ex.getMessage(), "CUSTOMER_NOT_FOUND", 404),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CabNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleCabNotFound(CabNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(false, ex.getMessage(), "CAB_NOT_FOUND", 404),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(CabUnavailableException.class)
    public ResponseEntity<ApiErrorResponse> handleCabUnavailable(CabUnavailableException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(false, ex.getMessage(), "CAB_SERVICE_UNAVAILABLE", 503),
                HttpStatus.SERVICE_UNAVAILABLE
        );
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleBookingNotFound(BookingNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(false, ex.getMessage(), "CAB_NOT_FOUND", 404),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> handleBusiness(RuntimeException ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(false, ex.getMessage(), "BUSINESS_ERROR", 400),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {
        return new ResponseEntity<>(
                new ApiErrorResponse(false, "Internal Server Error", "INTERNAL_ERROR", 500),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
