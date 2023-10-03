package com.abdullahturhan.service;

import com.abdullahturhan.dto.SearchTaxiResponse;
import com.abdullahturhan.dto.TaxiDto;
import com.abdullahturhan.exception.TaxiNotAvailableException;
import com.abdullahturhan.exception.TaxiNotFoundException;
import com.abdullahturhan.model.Driver;
import com.abdullahturhan.model.Taxi;
import com.abdullahturhan.model.TaxiStatus;
import com.abdullahturhan.repository.TaxiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaxiService {
    private final TaxiRepository taxiRepository;

    public TaxiService(TaxiRepository taxiRepository) {
        this.taxiRepository = taxiRepository;
    }

    public SearchTaxiResponse getTaxiById(Long id){
        Optional<Taxi> optionalTaxi = taxiRepository.findById(id);
        if(optionalTaxi.isPresent()){
            return SearchTaxiResponse.builder()
                    .id(optionalTaxi.get().getId())
                    .firstName(optionalTaxi.get().getDriver().getFirstName())
                    .lastName(optionalTaxi.get().getDriver().getLastName())
                    .tripPoint(optionalTaxi.get().getTripPoint())
                    .taxiStatus(optionalTaxi.get().getTaxiStatus())
                    .build();
        }
        throw new TaxiNotFoundException("Taxi not found with id: " + id);
    }

    public List<SearchTaxiResponse> getTaxiListByStatus(){
        final TaxiStatus status = TaxiStatus.EMPTY;
        List<Taxi> taxiList = taxiRepository.findTaxiByTaxiStatus(status);
        if (!taxiList.isEmpty()) {
            return taxiList.stream().map(this::converter).collect(Collectors.toList());
        }
        throw new TaxiNotFoundException("Taxi not found !! they all are busy");
    }
    @Transactional
    public TaxiDto registerTaxi(TaxiDto taxiDto){
        final Driver driver = Driver.builder()
                .firstName(taxiDto.getFirstName())
                .lastName(taxiDto.getLastName())
                .build();
        final Taxi taxi = Taxi.builder()
                .driver(driver)
                .taxiStatus(taxiDto.getTaxiStatus())
                .build();
        final Taxi taxiFromDb = taxiRepository.save(taxi);

        log.info("Registered: " + taxiFromDb.toString());

        return TaxiDto.builder()
                .firstName(taxiFromDb.getDriver().getFirstName())
                .lastName(taxiFromDb.getDriver().getLastName())
                .taxiStatus(taxiFromDb.getTaxiStatus())
                .build();

    }
    @Transactional
    public void updateStatusToFull(Long taxiId){
        Taxi taxi = taxiRepository.findById(taxiId).orElseThrow(()-> new TaxiNotFoundException("Taxi not found"));
        TaxiStatus status = TaxiStatus.FULL;
        if (taxi.getTaxiStatus().equals(status)){
            throw new TaxiNotAvailableException("this taxi %s is full try another please!" );
        }
        taxi.setTaxiStatus(status);
        taxiRepository.save(taxi);
    }
    @Transactional
    public void  updateStatusToEmpty(Long taxiId){
        Taxi taxi = taxiRepository.findById(taxiId).orElseThrow(()-> new TaxiNotFoundException("Taxi not found"));
        TaxiStatus status = TaxiStatus.EMPTY;
        if (taxi.getTaxiStatus().equals(status)){
            throw new TaxiNotAvailableException("this taxi %s is also empty try another please!" );
        }
        taxi.setTaxiStatus(status);
        taxiRepository.save(taxi);
    }
    @Transactional
    public void deleteTaxi(Long id){
        Taxi taxi = taxiRepository.findById(id).orElseThrow(()-> new TaxiNotFoundException("Taxi not found"));
        taxiRepository.delete(taxi);

    }

    public SearchTaxiResponse converter(Taxi taxi){
        return SearchTaxiResponse.builder()
                .id(taxi.getId())
                .firstName(taxi.getDriver().getFirstName())
                .lastName(taxi.getDriver().getLastName())
                .taxiStatus(taxi.getTaxiStatus())
                .tripPoint(taxi.getTripPoint())
                .build();
    }



}
