package com.abdullahturhan.repository;

import com.abdullahturhan.model.Taxi;
import com.abdullahturhan.model.TaxiStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxiRepository extends JpaRepository<Taxi,Long> {
    List<Taxi> findTaxiByTaxiStatus(TaxiStatus status);
}
