package com.abdullahturhan.controller;

import com.abdullahturhan.dto.FinishTravelRequest;
import com.abdullahturhan.dto.SearchPastTravelsResponse;
import com.abdullahturhan.dto.TravelRequest;
import com.abdullahturhan.dto.TravelResponse;
import com.abdullahturhan.service.TravelService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/travel")
public class TravelController {
    private final TravelService travelService;

    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }
    @GetMapping(path = "/getTaxiList")
    @CircuitBreaker(name="gettingTaxiList",fallbackMethod = "getTaxiListFallback")
    public ResponseEntity<List> getTaxiList(){
        return ResponseEntity.status(HttpStatus.FOUND).body(travelService.getTaxisList());
    }
    @PostMapping(path = "/startTravel")
    public ResponseEntity<TravelResponse> startTravel(@RequestBody TravelRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(travelService.startTravel(request));
    }
    @PostMapping(path = "/finishTravel")
    public ResponseEntity<TravelResponse> finishTravel(@RequestBody FinishTravelRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(travelService.finishTravel(request));
    }
    @GetMapping(path = "/past-travel")
    public ResponseEntity<List<SearchPastTravelsResponse>> getPastTravels(@RequestParam(name = "customer_id", required = false, defaultValue = "Guest") Long customer_id){
        return ResponseEntity.status(HttpStatus.FOUND).body(travelService.getSearchPastTravels(customer_id));
    }

    public String getTaxiListFallback(Throwable throwable){
        return "Something went wrong, please try again later.";
    }
}
