package com.airport.weather;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
/**
 * simplify registration of rest and components using package-scanning.
 */
@Component
public class WeatherServerApp extends ResourceConfig {
    public WeatherServerApp() {
        register(JacksonFeature.class);
        packages("com.airport.trial.weather.rest");
    }
}
