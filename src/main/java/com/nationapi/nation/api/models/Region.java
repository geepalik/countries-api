package com.nationapi.nation.api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "regions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "region_id")
    private Long region_id;
    @Column(name = "name")
    private String name;
    @Column(name = "continent_id")
    @JsonIgnore
    private Long continentId;
    @ManyToOne
    @MapsId("continentId")
    @JoinColumn(name = "continent_id", insertable = false, updatable = false)
    @JsonIgnore
    private Continent continent;
}
