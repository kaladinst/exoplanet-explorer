package com.nasa.exoplanet_explorer.service;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NasaExoplanetService {
    private static final String NASA_API_URL =
            "https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=" +
                    "select+pl_name,pl_rade,pl_bmasse,pl_dens,pl_eqt,pl_insol,discoverymethod,disc_year+" +
                    "from+ps+where+default_flag=1&format=json";

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    private static class NasaPlanetDTO {
        @JsonProperty("pl_name")
        private String plName;

        @JsonProperty("pl_rade")
        private Double plRade;

        @JsonProperty("pl_bmasse")
        private Double plBmasse;

        @JsonProperty("pl_dens")
        private Double plDens;

        @JsonProperty("pl_eqt")
        private Integer plEqt;

        @JsonProperty("pl_insol")
        private Double plInsol;

        @JsonProperty("disc_year")
        private Integer discYear;

        @JsonProperty("discoverymethod")
        private String discoveryMethod;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fetchExoplanets() {
        RestTemplate restTemplate = new RestTemplate();
        NasaPlanetDTO[] planetResponse = restTemplate.getForObject(NASA_API_URL, NasaPlanetDTO[].class);

        if(planetResponse != null && planetResponse.length != 0) {
                for(NasaPlanetDTO planet : planetResponse) {
                    System.out.println(planet.getPlName());
                }
        } else {
            System.out.println("No Data Found");
            return;
        }
    }

    private double calculateComponent(double planetValue , double earthValue, double weight) {
        double top = Math.abs(planetValue - earthValue);
        double bottom = planetValue + earthValue;
        double fraction = top / bottom;

        return Math.pow(1.0 - fraction, weight / 4.0);
    }

    private double calculateESI(NasaPlanetDTO dto) {
        if(!(dto.getPlRade() == null || dto.getPlDens() == null || dto.getPlEqt() == null || dto.getPlBmasse() == null)) {
            double radius = dto.getPlRade();
            double density = dto.getPlDens();
            Integer temp = dto.getPlEqt();
            double mass = dto.getPlBmasse();

            double escapeVel = Math.sqrt(mass / radius);
            
            return 2;

        } else {
            return 0.0;
        }
    }
}


