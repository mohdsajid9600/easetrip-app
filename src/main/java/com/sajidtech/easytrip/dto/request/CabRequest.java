package com.sajidtech.easytrip.dto.request;

import lombok.Data;

@Data
public class CabRequest {

    private String cabNumber;
    private String cabModel;
    private double perKmRate;
}
