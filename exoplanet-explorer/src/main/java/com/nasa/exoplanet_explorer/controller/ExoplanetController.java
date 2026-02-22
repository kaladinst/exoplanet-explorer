package com.nasa.exoplanet_explorer.controller;

import com.nasa.exoplanet_explorer.model.Exoplanet;
import com.nasa.exoplanet_explorer.repository.ExoplanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
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
    @GetMapping("/between")
    public List<Exoplanet> getBetween(@RequestParam double min, @RequestParam double max) {
        return exoplanetRepository.findByEsiScoreBetween(min,max);
    }

}
