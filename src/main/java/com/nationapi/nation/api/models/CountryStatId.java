package com.nationapi.nation.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryStatId implements Serializable {
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "year")
    private int year;
}
