package com.nasa.exoplanet_explorer;

import com.nasa.exoplanet_explorer.service.NasaExoplanetService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ESICalculatorTest {

    @Test
    void testEarthSimilarity() throws Exception {
        NasaExoplanetService service = new NasaExoplanetService(null);

        Method method = NasaExoplanetService.class.getDeclaredMethod("calculateComponent", double.class, double.class, double.class);
        method.setAccessible(true);

        double score = (double) method.invoke(service, 1.0, 1.0, 0.57);

        assertEquals(1.0,score,0.001, "Earth should have a perfect radius score of 1.0");
    }
}
