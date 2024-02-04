package com.nationapi.nation.api.services;

import com.nationapi.nation.api.models.Country;
import com.nationapi.nation.api.models.dto.GetGlobalStatsRequestDto;
import com.nationapi.nation.api.models.dto.GlobalStatsDto;
import com.nationapi.nation.api.models.dto.MaxGdpPerPopulationDto;
import com.nationapi.nation.api.models.dto.PaginatedResponse;

import java.util.List;

public interface CountryService {
    List<Country> list();

    List<MaxGdpPerPopulationDto> getMaxGdpPerPopulation();

    PaginatedResponse<GlobalStatsDto> getGlobalStats(GetGlobalStatsRequestDto getGlobalStatsRequestDto);
}
