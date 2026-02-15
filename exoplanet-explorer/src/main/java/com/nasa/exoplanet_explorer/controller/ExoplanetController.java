package com.nasa.exoplanet_explorer.controller;

import com.nasa.exoplanet_explorer.model.Exoplanet;
import com.nasa.exoplanet_explorer.repository.ExoplanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
@RequiredArgsConstructor
public class ExoplanetController {
    private final ExoplanetRepository exoplanetRepository;

    @GetMapping("/top-10")
    public List<Exoplanet> getTop10Planets() {
        return exoplanetRepository.findTop10ByOrderByEsiScoreDesc();
    }

    @GetMapping("/all")
    public List<Exoplanet> getAllPlanets() {
        return exoplanetRepository.findAll();
    }
}
