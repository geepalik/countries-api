package com.nationapi.nation.api.services;

import com.nationapi.nation.api.models.Countries;

import java.util.Collection;

public interface CountriesService {
    Collection<Countries> list();
}
