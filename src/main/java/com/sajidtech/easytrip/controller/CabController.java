package com.sajidtech.easytrip.controller;

import com.sajidtech.easytrip.dto.request.CabRequest;
import com.sajidtech.easytrip.dto.response.CabResponse;
import com.sajidtech.easytrip.dto.response.DriverResponse;
import com.sajidtech.easytrip.model.Cab;
import com.sajidtech.easytrip.model.Driver;
import com.sajidtech.easytrip.service.CabService;
import com.sajidtech.easytrip.service.DriverService;
import com.sajidtech.easytrip.transformer.CabTransformer;
import com.sajidtech.easytrip.transformer.DriverTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    private CabService cabService;

    @Autowired
    private DriverService driverService;

    @PostMapping("/add/{id}")
    public ResponseEntity<CabResponse> createCab(@RequestBody CabRequest cabRequest, @PathVariable("id") int id){
        CabResponse cabResponse =  cabService.createCab(cabRequest, id);
        return new ResponseEntity<>(cabResponse, HttpStatus.CREATED);
    }
}
