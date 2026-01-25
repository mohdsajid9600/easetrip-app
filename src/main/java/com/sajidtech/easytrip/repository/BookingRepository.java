package com.sajidtech.easytrip.repository;

import com.sajidtech.easytrip.enums.TripStatus;
import com.sajidtech.easytrip.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {

    @Query("select b from Booking b where b.tripStatus= :status")
    List<Booking> findByTripStatus(@Param("status") TripStatus tripStatus);
}
