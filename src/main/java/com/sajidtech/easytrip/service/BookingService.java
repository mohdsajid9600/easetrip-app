package com.sajidtech.easytrip.service;


import com.sajidtech.easytrip.Enum.Status;
import com.sajidtech.easytrip.Enum.TripStatus;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
        Customer customer = checkValidCustomer(customerId);

        boolean hasOldBooking = customer.getBooking().stream().anyMatch(b-> b.getTripStatus().equals((TripStatus.IN_PROGRESS)));
        if(hasOldBooking){
            throw new RuntimeException("The customer has already been One Journey which status is IN_PROGRESS");
        }

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

        BookingResponse bookingResponse = BookingTransformer.bookingToBookingResponse(savedBooking,availableCab,savedDriver,savedCustomer);

        //EmailSender to the customer who ever booked the cab
//        sendEmail(bookingResponse);

        return bookingResponse;
    }

    public BookingResponse updateBookedDetails(BookingRequest bookingRequest, int customerId) {

        Customer customer = checkValidCustomer(customerId);

        Booking booking = customer.getBooking().stream().filter((b) -> b.getTripStatus().equals(TripStatus.IN_PROGRESS)).findAny()
                .orElseThrow(()-> new BookingNotFound("Customer has no one Booking which is IN_PROGRESS"));

        Optional<Integer> OptionalDriverId = bookingRepository.getDriverIdByBookingId(booking.getBookingId());
        if(OptionalDriverId.isEmpty()){
            throw new DriverIdNotFoundException("We are not fetch DriverId from SQL");
        }

        int driverId = OptionalDriverId.get();
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(()-> new DriverNotFoundException("Driver Not Found"));

        booking.setPickup(bookingRequest.getPickup());
        booking.setDestination(bookingRequest.getDestination());
        booking.setTripDistanceInKm(bookingRequest.getTripDistanceInKm());
        booking.setBillAmount(bookingRequest.getTripDistanceInKm() * driver.getCab().getPerKmRate());
        Booking savedBooking =  bookingRepository.save(booking);

        return BookingTransformer.bookingToBookingResponse(savedBooking, driver.getCab(), driver, customer);
    }

    public void cancelBooking(int customerId) {
        Customer customer = checkValidCustomer(customerId);

        Booking booking = customer.getBooking().stream().filter((b) -> b.getTripStatus().equals(TripStatus.IN_PROGRESS)).findAny()
                .orElseThrow(()-> new BookingNotFound("Customer has no one Booking which is IN_PROGRESS"));

        booking.setTripStatus(TripStatus.CANCELLED);
        bookingRepository.save(booking);

        Optional<Integer> OptionalDriverId  = bookingRepository.getDriverIdByBookingId(booking.getBookingId());
        OptionalDriverId.orElseThrow(()-> new NotFetchDriverId("Not Fetch driver id from database SQL Exception"));

        int driverId = OptionalDriverId.get();
        Driver driver = driverRepository.findById(driverId).orElseThrow(()-> new DriverNotFoundException("Driver Id is Invalid at the service layer"));

        driver.getCab().setAvailable(true);
        driverRepository.save(driver);
    }

    public void completeBookingByDriver(int driverId) {
       Driver driver = checkValidDriver(driverId);

       Booking booking = driver.getBooking().stream().filter(b -> b.getTripStatus().equals(TripStatus.IN_PROGRESS))
              .findAny().orElseThrow(() -> new BookingNotFound("No one Booking is available in Driver List to the completion"));

           booking.setTripStatus(TripStatus.COMPLETED);
           driver.getCab().setAvailable(true);
           driverRepository.save(driver);
    }

    private Driver checkValidDriver(int driverId) {
       Driver driver = driverRepository.findById(driverId).orElseThrow(()-> new DriverNotFoundException("Invalid Driver ID"));
       if(driver.getStatus() == Status.INACTIVE){
           throw new RuntimeException("Driver is Inactive, Access denied ");
       }
       return driver;
    }

    private Customer checkValidCustomer(int customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Customer Id is Invalid with : "+customerId));
        if(customer.getStatus() == Status.INACTIVE){
            throw new RuntimeException("Customer is inactive. Access denied");
        }
        return customer;
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
}
