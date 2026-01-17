package com.sajidtech.easytrip.repository;

import com.sajidtech.easytrip.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Integer> {
}
