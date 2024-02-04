package com.nationapi.nation.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalStatsDto {
    private String continentName;
    private String regionName;
    private String countryName;
    private Integer countryStatsYear;
    private Integer countryStatsPopulation;
    private BigDecimal countryStatsGdp;
}
