package com.nationapi.nation.api.controllers;

import com.nationapi.nation.api.models.dto.GetGlobalStatsRequestDto;
import com.nationapi.nation.api.models.dto.GlobalStatsDto;
import com.nationapi.nation.api.models.dto.PaginatedResponse;
import com.nationapi.nation.api.output.GlobalStatsResponse;
import com.nationapi.nation.api.output.Response;
import com.nationapi.nation.api.services.implementation.CountryServiceImpl;
import com.nationapi.nation.api.services.implementation.RegionServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nation-api")
@Validated
@RequiredArgsConstructor
public class CountryController {
    private final CountryServiceImpl countryService;
    private final RegionServiceImpl regionService;

    @GetMapping("/countries-languages")
    public ResponseEntity<Response> getCountries(){
        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("countries", countryService.list()))
                        .message("Countries and their languages")
                        .build()
        );
    }

    @GetMapping("/countries-stats-max-gdp")
    public ResponseEntity<Response> getMaxGdpPerPopulationRatio(){
        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("stats", countryService.getMaxGdpPerPopulation()))
                        .message("Countries stats and maximum GDP per population ration along the years")
                        .build()
        );
    }

    @GetMapping("/regions")
    public ResponseEntity<Response> getRegions(){
        return ResponseEntity.ok(
                Response.builder()
                        .data(Map.of("regions", regionService.list()))
                        .message("Regions list")
                        .build()
        );
    }

    @GetMapping("/global-stats")
    public ResponseEntity<GlobalStatsResponse> getGlobalStats(
            @ModelAttribute @Valid GetGlobalStatsRequestDto getGlobalStatsRequestDto
    ){
        PaginatedResponse<GlobalStatsDto> globalStats = countryService.getGlobalStats(getGlobalStatsRequestDto);
        return ResponseEntity.ok(
                GlobalStatsResponse.builder()
                        .data(globalStats)
                        .message("Global Filtered Stats")
                        .build()
        );
    }
}
