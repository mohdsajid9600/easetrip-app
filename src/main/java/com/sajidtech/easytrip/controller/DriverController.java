package com.sajidtech.easytrip.controller;

import com.sajidtech.easytrip.dto.request.DriverRequest;
import com.sajidtech.easytrip.dto.response.DriverResponse;
import com.sajidtech.easytrip.model.Driver;
import com.sajidtech.easytrip.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/add")
    public ResponseEntity<DriverResponse> addDriverInfo(@RequestBody DriverRequest driverRequest){
        DriverResponse driverResponse = driverService.addDriverInfo(driverRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(driverResponse);
    }

    @GetMapping("get-driver/{id}")
    public ResponseEntity<DriverResponse> getDriverById(@PathVariable("id") int id){
        DriverResponse driverResponse = driverService.getDriverById(id);
        return new ResponseEntity<>(driverResponse, HttpStatus.FOUND);
    }
}
