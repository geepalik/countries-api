package com.nationapi.nation.api.services.implementation;

import com.nationapi.nation.api.models.Countries;
import com.nationapi.nation.api.repositories.CountriesRepo;
import com.nationapi.nation.api.services.CountriesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CountriesServiceImpl implements CountriesService {
    private final CountriesRepo countriesRepo;
    @Override
    public Collection<Countries> list() {
        log.info("Fetching countries ans their languages");
        return countriesRepo.findAll();
    }
}
