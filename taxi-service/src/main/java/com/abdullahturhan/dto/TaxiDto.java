package com.abdullahturhan.dto;

import com.abdullahturhan.model.TaxiStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class TaxiDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private TaxiStatus taxiStatus;
}