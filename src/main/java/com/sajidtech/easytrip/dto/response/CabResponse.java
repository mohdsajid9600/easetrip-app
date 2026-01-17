package com.sajidtech.easytrip.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CabResponse {
    private String cabNumber;
    private String cabModel;
    private double perKmRate;
    private boolean available;
}
