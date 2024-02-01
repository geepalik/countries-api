package com.nationapi.nation.api.services;

import com.nationapi.nation.api.models.Country;
import com.nationapi.nation.api.models.dto.MaxGdpPerPopulationDto;

import java.util.List;

public interface CountryService {
    List<Country> list();

    List<MaxGdpPerPopulationDto> getMaxGdpPerPopulation();
}
