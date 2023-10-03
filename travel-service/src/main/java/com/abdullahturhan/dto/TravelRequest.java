package com.abdullahturhan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelRequest {
    @NotBlank
    private Long taxi_id;
    @NotBlank
    private Long customer_id;
    @NotBlank
    private String fromWhere;
    @NotBlank
    private String toWhere;

}
