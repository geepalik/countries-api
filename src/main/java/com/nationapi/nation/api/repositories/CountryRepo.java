package com.nationapi.nation.api.repositories;

import com.nationapi.nation.api.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Long> {
}
