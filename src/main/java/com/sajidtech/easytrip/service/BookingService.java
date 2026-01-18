package com.sajidtech.easytrip.service;


import com.sajidtech.easytrip.dto.request.BookingRequest;
import com.sajidtech.easytrip.dto.response.BookingResponse;
import com.sajidtech.easytrip.exception.CabUnavailabaleException;
import com.sajidtech.easytrip.exception.CustomerNotFoundException;
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
        sendEmail(bookingResponse);
        return bookingResponse;
    }

    private void sendEmail(BookingResponse booking){
        String formate =
                "Dear "+booking.getCustomerResponse().getName()+",\n\n" +
                        "Thank you for choosing EasyTrip!\n\n" +
                        "Your cab booking has been successfully confirmed. Please find your trip details below:\n\n" +
                        "========================================\n" +
                        "Passenger Name : "+booking.getCustomerResponse().getName()+"\n\n" +
                        "Pickup Point   :"+booking.getPickup()+"\n" +
                        "Drop Point     : "+booking.getDestination()+"\n\n" +
                        "Total Fare     : ₹"+booking.getBillAmount()+"\n\n" +
                        "Cab Model      : "+booking.getCabResponse().getCabModel()+"\n" +
                        "Cab Number     : "+booking.getCabResponse().getCabNumber()+"\n\n" +
                        "Driver Name    : "+booking.getCabResponse().getDriverResponse().getName()+"\n" +
                        "Driver Email   : "+booking.getCabResponse().getDriverResponse().getEmail()+"\n" +
                        "========================================\n\n" +
                        "Your cab will reach your pickup location shortly.\n\n" +
                        "We wish you a safe and comfortable journey with EasyTrip.\n\n" +
                        "Regards,\n" +
                        "EasyTrip Support Team\n" +
                        "Customer Care: +91-90000-00000\n" +
                        "Email: support@easytrip.com";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("easetriptrevler@gmail.com");
        simpleMailMessage.setTo(booking.getCustomerResponse().getEmail());
        simpleMailMessage.setSubject("EasyTrip Booking Confirmation.");
        simpleMailMessage.setText(formate);
        javaMailSender.send(simpleMailMessage);
    }
}
