package com.sajidtech.easytrip.service;

import com.sajidtech.easytrip.exception.CabNotFoundException;
import com.sajidtech.easytrip.model.Cab;
import com.sajidtech.easytrip.repository.CabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabService {

    @Autowired
    private CabRepository cabRepository;

    public Cab createCab(Cab cab) {
        cab.setAvailable(true);
        return cabRepository.save(cab);
    }

    public Cab getCabById(int id) {
        return cabRepository.findById(id).orElseThrow(() -> new CabNotFoundException("Cab ID is Invalid with : "+id));
    }
}
