package com.sajidtech.easytrip.emailTemplate;

import com.sajidtech.easytrip.dto.response.BookingResponse;

public class EmailTemplate {

    public static String bookingConfirmationTemplate(BookingResponse booking) {

        return "Dear " + booking.getCustomerResponse().getName() + ",\n\n" +

                "Thank you for choosing EasyTrip!\n\n" +

                "Your cab booking has been successfully confirmed. Please find your trip details below:\n\n" +

                "========================================\n" +
                "Passenger Name : " + booking.getCustomerResponse().getName() + "\n" +
                "Pickup Point   : " + booking.getPickup() + "\n" +
                "Drop Point     : " + booking.getDestination() + "\n" +
                "Total Fare     : ₹" + booking.getBillAmount() + "\n\n" +

                "Cab Model      : " + booking.getCabResponse().getCabModel() + "\n" +
                "Cab Number     : " + booking.getCabResponse().getCabNumber() + "\n\n" +

                "Driver Name    : " + booking.getCabResponse().getDriverResponse().getName() + "\n" +
                "Driver Email   : " + booking.getCabResponse().getDriverResponse().getEmail() + "\n" +
                "========================================\n\n" +

                "Your cab will reach your pickup location shortly.\n\n" +

                "We wish you a safe and comfortable journey with EasyTrip.\n\n" +

                "Regards,\n" +
                "EasyTrip Support Team\n" +
                "Customer Care: +91-90000-00000\n" +
                "Email: support@easytrip.com";
    }
}
