package com.nationapi.nation.api.services.implementation;

import com.nationapi.nation.api.models.Country;
import com.nationapi.nation.api.repositories.CountryRepo;
import com.nationapi.nation.api.services.CountryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CountryServiceImpl implements CountryService {
    private final CountryRepo countryRepo;
    @Override
    public Collection<Country> list() {
        log.info("Fetching countries and their languages");
        return countryRepo.findAll();
    }
}
