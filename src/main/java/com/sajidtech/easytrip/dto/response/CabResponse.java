package com.sajidtech.easytrip.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sajidtech.easytrip.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cabNumber",
        "cabModel",
        "perKmRate",
        "available",
        "status",
        "driverResponse"
})
public class CabResponse {

    private String cabNumber;
    private String cabModel;
    private Double perKmRate;
    private Boolean available;
    private Status status;
    private DriverResponse driverResponse;
}
