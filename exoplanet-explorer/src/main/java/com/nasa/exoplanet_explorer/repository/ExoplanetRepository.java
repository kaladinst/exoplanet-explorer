package com.nasa.exoplanet_explorer.repository;

import com.nasa.exoplanet_explorer.model.Exoplanet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExoplanetRepository extends JpaRepository<Exoplanet, Long> {
    boolean existsByName(String name);

    List<Exoplanet> findTop10ByOrderByEsiScoreDesc();
    List<Exoplanet> findByEsiScoreBetween( double min, double max);
}
