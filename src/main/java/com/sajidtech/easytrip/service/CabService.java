package com.sajidtech.easytrip.service;

import com.sajidtech.easytrip.dto.request.CabRequest;
import com.sajidtech.easytrip.dto.response.CabResponse;
import com.sajidtech.easytrip.exception.CabNotFoundException;
import com.sajidtech.easytrip.exception.DriverNotFoundException;
import com.sajidtech.easytrip.model.Cab;
import com.sajidtech.easytrip.model.Driver;
import com.sajidtech.easytrip.repository.CabRepository;
import com.sajidtech.easytrip.repository.DriverRepository;
import com.sajidtech.easytrip.transformer.CabTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabService {

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverRepository driverRepository;

    public CabResponse createCab(CabRequest cabRequest, int driverId) {
        Cab cab = CabTransformer.CabRequestToCab(cabRequest);
        Driver driver = driverRepository.findById(driverId).orElseThrow(()-> new DriverNotFoundException("Driver ID Does not Exist for the Cab: "+driverId));
        driver.setCab(cab);
        Driver savedDriver = driverRepository.save(driver);
        return CabTransformer.CabToCabResponse(savedDriver.getCab(), savedDriver);
    }
}
