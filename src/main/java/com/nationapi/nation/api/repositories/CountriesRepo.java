package com.nationapi.nation.api.repositories;

import com.nationapi.nation.api.models.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepo extends JpaRepository<Countries, Long> {
}
