package com.nasa.exoplanet_explorer.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exoplanets")
public class Exoplanet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double radius;
    private Double mass;
    private Double density;
    private Double flux;
    private Integer equilibriumTemp;

    private Double esiScore;

    private String discoveryMethod;
    private Integer discoveryYear;
}
