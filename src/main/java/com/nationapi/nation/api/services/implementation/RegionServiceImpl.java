package com.nationapi.nation.api.services.implementation;

import com.nationapi.nation.api.models.Region;
import com.nationapi.nation.api.repositories.RegionRepo;
import com.nationapi.nation.api.services.RegionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class RegionServiceImpl implements RegionService {
    private final RegionRepo regionRepo;
    @Override
    public List<Region> list() {
        log.info("Fetching all regions");
        return regionRepo.findAll();
    }
}
