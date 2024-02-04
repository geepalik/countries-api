package com.nationapi.nation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    @JsonIgnore
    private Long country_id;
    @Column(name = "name")
    private String name;
    @Column(name = "area")
    private double area;
    @Column(name = "national_day")
    @JsonIgnore
    private LocalDate national_day;
    @Column(name = "country_code2")
    private String country_code2;
    @Column(name = "country_code3")
    @JsonIgnore
    private String country_code3;
    @Column(name = "region_id")
    @JsonIgnore
    private Long regionId;
    @ManyToOne
    @MapsId("regionId")
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    @JsonIgnore
    private Region region;

    @ManyToMany
    @JoinTable(
            name = "country_languages",
            joinColumns = @JoinColumn(name = "country_id", referencedColumnName = "country_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "language_id"))
    private List<Language> languages;
}
