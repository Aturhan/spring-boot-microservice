package com.abdullahturhan.dto;

import com.abdullahturhan.model.TravelHasFinished;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchPastTravelsResponse {
    private Long id;
    private String fromWhere;
    private String toWhere;
    private Double price;
    private Long customer_id;
    private Long taxi_id;
    private TravelHasFinished travelHasFinished;
    private LocalDateTime travelDate;
}
