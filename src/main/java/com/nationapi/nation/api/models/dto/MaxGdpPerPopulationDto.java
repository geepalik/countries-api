package com.nationapi.nation.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaxGdpPerPopulationDto {
    private String name;
    private String country_code3;
    private int year;
    private int population;
    private BigDecimal gdp;
}
