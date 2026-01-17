package com.sajidtech.easytrip.transformer;

import com.sajidtech.easytrip.dto.request.DriverRequest;
import com.sajidtech.easytrip.dto.response.DriverResponse;
import com.sajidtech.easytrip.model.Driver;

public class DriverTransformer {

    public static Driver driverRequestToDriver(DriverRequest driverRequest){

        return Driver.builder()
                .name(driverRequest.getName())
                .age(driverRequest.getAge())
                .email(driverRequest.getEmail())
                .build();
    }

    public static DriverResponse DriverToDriverResponse(Driver driver){
        return DriverResponse.builder()
                .name(driver.getName())
                .age(driver.getAge())
                .email(driver.getEmail())
                .driverId(driver.getDriveId())
                .build();
    }
}
