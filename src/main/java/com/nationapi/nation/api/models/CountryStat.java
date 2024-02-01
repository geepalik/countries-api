package com.nationapi.nation.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "country_stats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryStat {
    @EmbeddedId
    private CountryStatId id;
    @ManyToOne
    @MapsId("countryId")
    @JoinColumn(name = "country_id", insertable=false, updatable=false)
    private Country country;
    @Column(name = "population")
    private int population;
    @Column(name = "gdp")
    private BigDecimal gdp;
}
