package com.sajidtech.easytrip.repository;

import com.sajidtech.easytrip.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    @Query(value = "select * from booking  where customer_id = :customerId", nativeQuery = true)
    Optional<Booking> hasBookedDetails(@Param("customerId") int customerId);

    @Query(value = "select driver_id from booking where customer_id = :customerId", nativeQuery = true)
    Optional<Integer> getDriverIdByCustomerId(@Param("customerId") int customerId);

    @Query(value = "select driver_id from booking where booking_id = :id", nativeQuery = true)
    Optional<Integer> getDriverIdByBookingId(@Param("id") int bookingId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE booking SET driver_id = NULL WHERE booking_id = :bookingId", nativeQuery = true)
    void removeDriverInBooking(@Param("bookingId") int bookingId);
}
