package com.sajidtech.easytrip.repository;

import com.sajidtech.easytrip.model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {
}
