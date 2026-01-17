package com.sajidtech.easytrip.dto.response;

import com.sajidtech.easytrip.model.Booking;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class DriverResponse {

    private int driverId;
    private String name;
    private int age;
    private String email;
}
