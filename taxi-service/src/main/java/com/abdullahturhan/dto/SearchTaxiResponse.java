package com.abdullahturhan.dto;

import com.abdullahturhan.model.TaxiStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchTaxiResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private TaxiStatus taxiStatus;
    private Double tripPoint;
}