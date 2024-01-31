package com.nationapi.nation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Countries {
    @Id
    @JsonIgnore
    private Long country_id;
    private String name;
    private double area;
    @JsonIgnore
    private LocalDate national_day;
    private String country_code2;
    @JsonIgnore
    private String country_code3;
    @JsonIgnore
    private Long region_id;
}
