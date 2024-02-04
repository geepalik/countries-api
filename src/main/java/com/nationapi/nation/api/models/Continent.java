package com.nationapi.nation.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "continents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "continent_id")
    private Long continent_id;
    @Column(name = "name")
    private String name;
}
