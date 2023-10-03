package com.abdullahturhan.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinishTravelRequest {
    @NotBlank
    private Long taxi_id;
    @NotBlank
    private Long travel_id;
}
