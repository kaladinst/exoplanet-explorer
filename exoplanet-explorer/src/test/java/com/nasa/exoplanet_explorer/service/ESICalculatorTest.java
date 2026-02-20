package com.nasa.exoplanet_explorer.service;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ESICalculatorTest {

    @Test
    void testEarth() throws Exception {
        NasaExoplanetService service = new NasaExoplanetService(null);

        Method method = NasaExoplanetService.class.getDeclaredMethod("calculateComponent", double.class, double.class, double.class);
        method.setAccessible(true);

        double score = (double) method.invoke(service, 1.0, 1.0, 0.57);

        assertEquals(1.0,score,0.001, "Earth should have a perfect radius score of 1.0");
    }

    @Test
    void testExactPlanet() throws Exception {
        NasaExoplanetService service = new NasaExoplanetService(null);
        Method method = NasaExoplanetService.class.getDeclaredMethod("calculateComponent", double.class, double.class, double.class);
        method.setAccessible(true);

        double score = (double) method.invoke(service, 11.0, 1.0, 0.57);

        assertEquals(0.77, score, 0.01,  "Planet should have low radius");
    }

    @Test
    void testNull() throws Exception {
        NasaExoplanetService.NasaPlanetDTO fakePlanet = new NasaExoplanetService.NasaPlanetDTO();
        NasaExoplanetService service = new NasaExoplanetService(null);
        Method method = NasaExoplanetService.class.getDeclaredMethod("calculateESI", NasaExoplanetService.NasaPlanetDTO.class);
        fakePlanet.setPlRade(null);
        fakePlanet.setPlDens(1.0);
        fakePlanet.setPlBmasse(null);
        fakePlanet.setPlEqt(20);

        method.setAccessible(true);

        double esi = (double)method.invoke(service, fakePlanet);

        assertEquals(0.0, esi, "Planets attribute(s) is/are null hence 0.0");
    }

    @Test
    void testEsi() throws Exception {
        NasaExoplanetService.NasaPlanetDTO fakePlanet = new NasaExoplanetService.NasaPlanetDTO();
        NasaExoplanetService service = new NasaExoplanetService(null);
        Method method = NasaExoplanetService.class.getDeclaredMethod("calculateESI", NasaExoplanetService.NasaPlanetDTO.class);
        fakePlanet.setPlRade(11.0);
        fakePlanet.setPlDens(1.0);
        fakePlanet.setPlBmasse(5.0);
        fakePlanet.setPlEqt(20);

        method.setAccessible(true);

        double esi = (double)method.invoke(service, fakePlanet);

        assertEquals(0.03, esi, 0.002 , "Planets esi score is wrong");
    }


}
