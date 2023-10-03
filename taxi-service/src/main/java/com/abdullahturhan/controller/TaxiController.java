package com.abdullahturhan.controller;

import com.abdullahturhan.dto.SearchTaxiResponse;
import com.abdullahturhan.dto.TaxiDto;
import com.abdullahturhan.service.TaxiService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/taxi")
public class TaxiController {

    private final TaxiService taxiService;

    public TaxiController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<SearchTaxiResponse> SearchTaxiById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(taxiService.getTaxiById(id));
    }
    @GetMapping(path = "/status")
    public ResponseEntity<List<SearchTaxiResponse>> SearchTaxisByStatus() {
        return ResponseEntity.status(HttpStatus.FOUND).body(taxiService.getTaxiListByStatus());
    }

    @PostMapping(path = "/register")
    public ResponseEntity<TaxiDto> registerTaxi(@Valid @RequestBody TaxiDto taxiDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(taxiService.registerTaxi(taxiDto));
    }
    @PutMapping(path = "/changeToFull/{taxiId}")
    public ResponseEntity<Void> updateStatusToFull(@PathVariable Long taxiId){
        taxiService.updateStatusToFull(taxiId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PutMapping(path = "/changeToEmpty/{taxiId}")
    public ResponseEntity<Void> updateStatusToEmpty(@PathVariable("taxiId") Long taxiId){
        taxiService.updateStatusToEmpty(taxiId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTaxi(@PathVariable("id") Long id){
        taxiService.deleteTaxi(id);
        return ResponseEntity.status(204).build();
    }


}

