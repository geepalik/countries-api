package com.nationapi.nation.api.repositories;

import com.nationapi.nation.api.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepo extends JpaRepository<Region, Long> {
}
