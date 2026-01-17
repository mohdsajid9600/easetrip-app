package com.sajidtech.easytrip.transformer;

import com.sajidtech.easytrip.dto.request.CabRequest;
import com.sajidtech.easytrip.dto.response.CabResponse;
import com.sajidtech.easytrip.dto.response.DriverResponse;
import com.sajidtech.easytrip.model.Cab;
import com.sajidtech.easytrip.model.Driver;

public class CabTransformer {

    public static Cab CabRequestToCab(CabRequest cabRequest){
        return Cab.builder()
                .cabModel(cabRequest.getCabModel())
                .cabNumber(cabRequest.getCabNumber())
                .available(true)
                .perKmRate(cabRequest.getPerKmRate())
                .build();
    }

    public static CabResponse CabToCabResponse(Cab cab, Driver driver){
        return CabResponse.builder()
                .cabNumber(cab.getCabNumber())
                .cabModel(cab.getCabModel())
                .available(cab.isAvailable())
                .perKmRate(cab.getPerKmRate())
                .driverResponse(DriverTransformer.DriverToDriverResponse(driver))
                .build();
    }

}
