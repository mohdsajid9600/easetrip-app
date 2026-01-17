package com.sajidtech.easytrip.service;

import com.sajidtech.easytrip.dto.request.DriverRequest;
import com.sajidtech.easytrip.dto.response.DriverResponse;
import com.sajidtech.easytrip.exception.DriverNotFoundException;
import com.sajidtech.easytrip.model.Driver;
import com.sajidtech.easytrip.repository.DriverRepository;
import com.sajidtech.easytrip.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;


    public DriverResponse addDriverInfo(DriverRequest driverRequest) {
        Driver driver = DriverTransformer.driverRequestToDriver(driverRequest);
        Driver savedDriver = driverRepository.save(driver);
        return DriverTransformer.DriverToDriverResponse(savedDriver);
    }

    public DriverResponse getDriverById(int id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException("Driver ID Invalid with : "+id));
        return DriverTransformer.DriverToDriverResponse(driver);
    }
}
