package com.sajidtech.easytrip.service;


import com.sajidtech.easytrip.dto.request.BookingRequest;
import com.sajidtech.easytrip.dto.response.BookingResponse;
import com.sajidtech.easytrip.emailTemplate.EmailTemplate;
import com.sajidtech.easytrip.exception.*;
import com.sajidtech.easytrip.model.Booking;
import com.sajidtech.easytrip.model.Cab;
import com.sajidtech.easytrip.model.Customer;
import com.sajidtech.easytrip.model.Driver;
import com.sajidtech.easytrip.repository.BookingRepository;
import com.sajidtech.easytrip.repository.CabRepository;
import com.sajidtech.easytrip.repository.CustomerRepository;
import com.sajidtech.easytrip.repository.DriverRepository;
import com.sajidtech.easytrip.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()->
                new CustomerNotFoundException("Invalid customer Id for Booking : "+customerId));

        Cab availableCab  = cabRepository.getAvailableCab().orElseThrow(()->
                new CabUnavailabaleException(" Sorry !!  Cab Unavailable at this time"));
        Driver driver  = driverRepository.availableCabDriver(availableCab.getCabId());

        Booking booking = BookingTransformer.bookingRequestToBooking(bookingRequest, availableCab.getPerKmRate());
        Booking savedBooking = bookingRepository.save(booking);

        availableCab.setAvailable(false);
        driver.getBooking().add(savedBooking);
        customer.getBooking().add(savedBooking);

        Customer savedCustomer = customerRepository.save(customer);
        Driver savedDriver = driverRepository.save(driver);

        BookingResponse bookingResponse = BookingTransformer.bookingToBookingResponse(savedBooking, availableCab,savedDriver,savedCustomer);

        //EmailSender to the customer who ever booked the cab
        sendEmail(bookingResponse);

        return bookingResponse;
    }

    private void sendEmail(BookingResponse booking){

        String formate = EmailTemplate.bookingConfirmationTemplate(booking);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("easetriptrevler@gmail.com");
        simpleMailMessage.setTo(booking.getCustomerResponse().getEmail());
        simpleMailMessage.setSubject("EasyTrip Booking Confirmation.");
        simpleMailMessage.setText(formate);
        javaMailSender.send(simpleMailMessage);
    }

    public BookingResponse updateBookedDetails(BookingRequest bookingRequest, int customerId) {

        Optional<Integer> OptionalDriverId = bookingRepository.getDriverIdByCustomerId(customerId);

        if(OptionalDriverId.isEmpty()){
            throw new DriverIdNotFoundException("We are not fetch DriverId from SQL");
        }
        int driverId = OptionalDriverId.get();

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(()-> new DriverNotFoundException("Driver Not Found"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found at booking updation"));

        Optional<Booking> OptionalBooking = bookingRepository.updateBookedDetails(customerId);
        if(OptionalBooking.isEmpty()) {
            throw new BookingNotFound("Customer with id "+ customerId+" have no one booking yet!");
        }
        Booking booking = OptionalBooking.get();

        booking.setPickup(bookingRequest.getPickup());
        booking.setDestination(bookingRequest.getDestination());
        booking.setTripDistanceInKm(bookingRequest.getTripDistanceInKm());
        booking.setBillAmount(bookingRequest.getTripDistanceInKm() * driver.getCab().getPerKmRate());
        Booking savedBooking =  bookingRepository.save(booking);

        return BookingTransformer.bookingToBookingResponse(savedBooking, driver.getCab(), driver, customer);
    }
}
