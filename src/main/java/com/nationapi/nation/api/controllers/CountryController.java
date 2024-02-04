package com.nationapi.nation.api.controllers;

import com.nationapi.nation.api.output.Response;
import com.nationapi.nation.api.services.implementation.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nation-api")
@RequiredArgsConstructor
public class CountryController {
    private final CountryServiceImpl countryService;

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
}
