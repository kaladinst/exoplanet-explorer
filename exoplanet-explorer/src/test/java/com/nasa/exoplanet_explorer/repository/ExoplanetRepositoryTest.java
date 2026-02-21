package com.nasa.exoplanet_explorer.repository;

import com.nasa.exoplanet_explorer.model.Exoplanet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
public class ExoplanetRepositoryTest {

    @Autowired
    private ExoplanetRepository repository;

    @Test
    void testEsiScoreBetween() {
        Exoplanet planetLow = new Exoplanet();
        planetLow.setEsiScore(0.3);
        planetLow.setName("Low Planet");
        repository.save(planetLow);

        Exoplanet planetMid = new Exoplanet();
        planetMid.setEsiScore(0.5);
        planetMid.setName("Mid Planet");
        repository.save(planetMid);

        Exoplanet planetHigh = new Exoplanet();
        planetHigh.setEsiScore(0.8);
        planetHigh.setName("High Planet");
        repository.save(planetHigh);

        List<Exoplanet> result = repository.findByEsiScoreBetween(0.4,0.9);

        assertEquals(2, result.size(), "There should be only 2 planets that fit the description");

        boolean hasLowWorld = result.stream().anyMatch(p -> p.getName().equals("Low Planet"));
        assertFalse(hasLowWorld);

    }
}
