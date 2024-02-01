package com.nationapi.nation.api.services.implementation;

import com.nationapi.nation.api.models.Country;
import com.nationapi.nation.api.models.dto.MaxGdpPerPopulationDto;
import com.nationapi.nation.api.repositories.CountryRepo;
import com.nationapi.nation.api.services.CountryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CountryServiceImpl implements CountryService {
    private final CountryRepo countryRepo;
    private final EntityManager entityManager;
    @Override
    public List<Country> list() {
        log.info("Fetching countries and their languages");
        return countryRepo.findAll();
    }

    @Override
    public List<MaxGdpPerPopulationDto> getMaxGdpPerPopulation() {
        log.info("Fetching countries stats and maximum GDP per population ration along the years");
        String jpqlQuery = "WITH RankedStats AS (" +
                "  SELECT" +
                "    c.name AS name," +
                "    c.country_code3 AS country_code3," +
                "    cs.id.year AS year," +
                "    cs.population AS population," +
                "    cs.gdp AS gdp," +
                "    RANK() OVER (PARTITION BY cs.id.countryId ORDER BY cs.gdp / cs.population DESC) AS rnk" +
                "  FROM" +
                "    Country c" +
                "    JOIN CountryStat cs ON c.country_id = cs.id.countryId" +
                ") " +
                " SELECT NEW com.nationapi.nation.api.models.dto.MaxGdpPerPopulationDto(" +
                "    rs.name," +
                "    rs.country_code3," +
                "    rs.year," +
                "    rs.population," +
                "    rs.gdp" +
                ") " +
                " FROM RankedStats rs" +
                " WHERE rs.rnk = 1";
        TypedQuery<MaxGdpPerPopulationDto> query = entityManager.createQuery(jpqlQuery, MaxGdpPerPopulationDto.class);
        return query.getResultList();
    }
}
