package com.sajidtech.easytrip.controller;

import com.sajidtech.easytrip.model.Cab;
import com.sajidtech.easytrip.service.CabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    private CabService cabService;

    @PostMapping("/add")
    public Cab createCab(@RequestBody Cab cab){
        return cabService.createCab(cab);
    }
    @GetMapping("get-cab/{id}")
    public Cab getCabById(@PathVariable("id") int id){
        return cabService.getCabById(id);
    }
}
