package com.nationapi.nation.api.services;

import com.nationapi.nation.api.models.Country;

import java.util.Collection;

public interface CountryService {
    Collection<Country> list();
}
