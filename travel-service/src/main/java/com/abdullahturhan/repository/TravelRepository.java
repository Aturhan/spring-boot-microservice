package com.abdullahturhan.repository;

import com.abdullahturhan.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Long> {
    @Query("SELECT t FROM Travel t WHERE t.customer_id = :customer_id")
    List<Travel> findByCustomerId(Long customer_id);
}
