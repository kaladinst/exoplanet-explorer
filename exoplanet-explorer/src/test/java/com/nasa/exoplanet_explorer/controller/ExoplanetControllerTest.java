package com.nasa.exoplanet_explorer.controller;

import com.nasa.exoplanet_explorer.model.Exoplanet;
import com.nasa.exoplanet_explorer.repository.ExoplanetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ExoplanetController.class)
public class ExoplanetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ExoplanetRepository repository;



    @Test
    void testGetTop10() throws Exception {
        Exoplanet fakePlanet = new Exoplanet();
        List<Exoplanet> fakeList = List.of(fakePlanet);
        fakePlanet.setEsiScore(0.82);
        fakePlanet.setName("CORUSCANT");

        Mockito.when(repository.findTop10ByOrderByEsiScoreDesc()).thenReturn(fakeList);

        mockMvc.perform(get("/api/planets/top-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("CORUSCANT"));
    }


}
