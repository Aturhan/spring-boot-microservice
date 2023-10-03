package com.abdullahturhan.service;

import com.abdullahturhan.dto.FinishTravelRequest;
import com.abdullahturhan.dto.SearchPastTravelsResponse;
import com.abdullahturhan.dto.TravelRequest;
import com.abdullahturhan.dto.TravelResponse;
import com.abdullahturhan.exception.StartTravelException;
import com.abdullahturhan.exception.TravelNotFoundException;
import com.abdullahturhan.model.Travel;
import com.abdullahturhan.model.TravelHasFinished;
import com.abdullahturhan.repository.TravelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TravelService {

    private final TravelRepository travelRepository;
    private final WebClient.Builder webClient;

    public TravelService(TravelRepository travelRepository, WebClient.Builder webClient) {
        this.travelRepository = travelRepository;
        this.webClient = webClient;
    }

    public List getTaxisList(){

       List result =  webClient.build().get()
                .uri("http://taxi-service/api/v1/taxi/status")
                .retrieve()
                .bodyToMono(List.class)
                .block();

        if (result != null){
            log.info("result: "+result);
            return result;
        }
        throw new RuntimeException("result not found");
    }

    public TravelResponse startTravel(TravelRequest request){

       final Travel travel = Travel.builder()
               .customer_id(request.getCustomer_id())
                .taxi_id(request.getTaxi_id())
               .toWhere(request.getToWhere())
               .fromWhere(request.getFromWhere())
                .price(randomPriceGenerator())
               .travelHasFinished(TravelHasFinished.NOT_FINISHED)
                .build();

       final Travel travelFromDb =  travelRepository.save(travel);
       try {
           webClient.build().put().uri("http://taxi-service/api/v1/taxi/changeToFull/" +travelFromDb.getTaxi_id())
                   .retrieve()
                   .bodyToMono(Void.class)
                   .block();
       }catch (StartTravelException exception){
           throw new StartTravelException("chose taxi is not available try another please: "+exception.getMessage());
       }


       log.info("Saved travel: "+ travelFromDb.toString());

       return TravelResponse.builder()
               .id(travelFromDb.getTravel_id())
               .customer_id(travelFromDb.getCustomer_id())
               .taxi_id(travelFromDb.getTaxi_id())
               .fromWhere(travelFromDb.getFromWhere())
               .toWhere(travelFromDb.getToWhere())
               .price(travelFromDb.getPrice())
               .build();

    }

    public TravelResponse finishTravel(FinishTravelRequest request){
        Travel travel = travelRepository.findById(request.getTravel_id())
                .orElseThrow(()-> new TravelNotFoundException("Travel not found with id: "+request.getTravel_id()));

        webClient.build().put().uri("http://taxi-service/api/v1/taxi/changeToEmpty/"+request.getTaxi_id())
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        TravelHasFinished travelHasFinished = TravelHasFinished.NOT_FINISHED;

        if (travel.getTravelHasFinished().equals(travelHasFinished)){
            travel.setTravelHasFinished(TravelHasFinished.FINISHED);
            travelRepository.save(travel);
        }

        return TravelResponse.builder()
                .id(travel.getTravel_id())
                .customer_id(travel.getCustomer_id())
                .taxi_id(travel.getTaxi_id())
                .travelHasFinished(travel.getTravelHasFinished())
                .toWhere(travel.getToWhere())
                .fromWhere(travel.getFromWhere())
                .price(travel.getPrice())
                .build();

    }

    public List<SearchPastTravelsResponse> getSearchPastTravels(Long customer_id){
        List<Travel> travelList = travelRepository.findByCustomerId(customer_id);
        if(!travelList.isEmpty()){
            return travelList.stream().map(model-> SearchPastTravelsResponse.builder()
                    .id(model.getTravel_id())
                    .taxi_id(model.getTaxi_id())
                    .customer_id(model.getCustomer_id())
                    .price(model.getPrice())
                    .toWhere(model.getToWhere())
                    .fromWhere(model.getFromWhere())
                    .travelDate(model.getCreatedAt())
                    .travelHasFinished(model.getTravelHasFinished()).build()).collect(Collectors.toList());
        }
        throw new TravelNotFoundException("Travel not found please check your information's");

    }



    private Double randomPriceGenerator(){
        Random random = new Random();
        return 100 + (1000 - 10) * random.nextDouble();
    }



}
