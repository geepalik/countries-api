package com.nationapi.nation.api.services.implementation;

import com.nationapi.nation.api.models.Country;
import com.nationapi.nation.api.models.dto.GetGlobalStatsRequestDto;
import com.nationapi.nation.api.models.dto.GlobalStatsDto;
import com.nationapi.nation.api.models.dto.MaxGdpPerPopulationDto;
import com.nationapi.nation.api.models.dto.PaginatedResponse;
import com.nationapi.nation.api.repositories.CountryRepo;
import com.nationapi.nation.api.services.CountryService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

    @Override
    public PaginatedResponse<GlobalStatsDto> getGlobalStats(GetGlobalStatsRequestDto getGlobalStatsRequestDto) {
        log.info("Fetching global filtered stats across all countries");

        //fetch total record count
        long totalRecordCount = getTotalRecordCount(getGlobalStatsRequestDto);

        //calculate total pages
        int recordsPerPage = getGlobalStatsRequestDto.getLimit();
        int currentPage = getGlobalStatsRequestDto.getPage();
        int offset = (currentPage - 1) * recordsPerPage;
        int totalPages= calculateTotalPages(totalRecordCount, recordsPerPage);

        List<GlobalStatsDto> paginatedResults = getGlobalStatsData(getGlobalStatsRequestDto, offset);

        PaginatedResponse<GlobalStatsDto> paginatedResponse = new PaginatedResponse<>();
        paginatedResponse.setStats(paginatedResults);
        paginatedResponse.setCurrentPage(offset / getGlobalStatsRequestDto.getLimit() + 1);
        paginatedResponse.setTotalPages(totalPages);

        return paginatedResponse;
    }

    private int calculateTotalPages(long totalRecords, int recordsPerPage) {
        return (int) Math.ceil((double) totalRecords / recordsPerPage);
    }

    private List<GlobalStatsDto> getGlobalStatsData(
            GetGlobalStatsRequestDto getGlobalStatsRequestDto,
            int offset
    ){
        StringBuilder dataQuery = getGlobalStatsJpqlQuery();
        TypedQuery<GlobalStatsDto> query = entityManager.createQuery(dataQuery.toString(), GlobalStatsDto.class);

        query.setParameter("regionId", getGlobalStatsRequestDto.getRegionId());
        query.setParameter("from", getGlobalStatsRequestDto.getFrom());
        query.setParameter("to", getGlobalStatsRequestDto.getTo());
        query.setFirstResult(offset);
        query.setMaxResults(getGlobalStatsRequestDto.getLimit());

        return query.getResultList();
    }

    private long getTotalRecordCount(GetGlobalStatsRequestDto getGlobalStatsRequestDto){
        StringBuilder countJpqlQuery = countGlobalStatsJpqlQuery();
        Query countQuery = entityManager.createQuery(countJpqlQuery.toString());
        countQuery.setParameter("regionId", getGlobalStatsRequestDto.getRegionId());
        countQuery.setParameter("from", getGlobalStatsRequestDto.getFrom());
        countQuery.setParameter("to", getGlobalStatsRequestDto.getTo());

        return (long) countQuery.getSingleResult();
    }

    private StringBuilder getGlobalStatsJpqlQuery() {
        StringBuilder jpqlQuery = new StringBuilder("SELECT cont.name as continentName, " +
                " r.name as regionName, " +
                " c.name as countryName, " +
                " cs.id.year as countryStatsYear, " +
                " cs.population as countryStatsPopulation, " +
                " cs.gdp countryStatsGdp FROM" +
                " Continent cont" +
                " INNER JOIN Region r" +
                " ON cont.continent_id = r.continentId" +
                " INNER JOIN Country c" +
                " ON c.regionId = r.region_id" +
                " INNER JOIN CountryStat cs" +
                " ON cs.id.countryId = c.country_id" +
                " WHERE 1=1");

        jpqlQuery.append(" AND r.region_id = :regionId");
        jpqlQuery.append(" AND cs.id.year >= :from");
        jpqlQuery.append(" AND cs.id.year <= :to");
        return jpqlQuery;
    }

    private StringBuilder countGlobalStatsJpqlQuery() {
        StringBuilder jpqlQuery = new StringBuilder("SELECT COUNT(*) " +
                " FROM" +
                " Continent cont" +
                " INNER JOIN Region r" +
                " ON cont.continent_id = r.continentId" +
                " INNER JOIN Country c" +
                " ON c.regionId = r.region_id" +
                " INNER JOIN CountryStat cs" +
                " ON cs.id.countryId = c.country_id" +
                " WHERE 1=1");

        jpqlQuery.append(" AND r.region_id = :regionId");
        jpqlQuery.append(" AND cs.id.year >= :from");
        jpqlQuery.append(" AND cs.id.year <= :to");
        return jpqlQuery;
    }
}
