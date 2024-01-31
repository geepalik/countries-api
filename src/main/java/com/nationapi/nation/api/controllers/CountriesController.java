package com.nationapi.nation.api.controllers;

import com.nationapi.nation.api.output.Response;
import com.nationapi.nation.api.services.implementation.CountriesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/nation-api")
@RequiredArgsConstructor
public class CountriesController {
    private final CountriesServiceImpl countriesService;

    @GetMapping("/countries-languages")
    public ResponseEntity<Response> getCountries(){
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("countries", countriesService.list()))
                        .message("Countries and their languages")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
