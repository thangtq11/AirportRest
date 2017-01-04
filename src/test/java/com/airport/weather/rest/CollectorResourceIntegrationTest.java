package com.airport.weather.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CollectorResourceIntegrationTest {

    @Inject
    private AirportWeatherCollectorImpl airportResource;

    @Inject
    private AirportWeatherCollector airportWeatherCollector;

    @Test
    public void updatingWeatherForNonExistentAirportReturns406() throws Exception {
        Response response = airportWeatherCollector.updateWeather("FOO", "", "");
        assertThat(response.getStatus()).isEqualTo(406);
    }

    @Test
    public void gettingAirportsShouldReturnListOfAirports() {
        airportResource.addAirport("FOO", "49", "11");
        Response response = airportWeatherCollector.getAirports();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getEntity()).isNotNull().isInstanceOf(java.util.List.class);

        ArrayList<String> entity = (ArrayList<String>) response.getEntity();

        assertThat(entity).contains("FOO");
        response = airportResource.deleteAirport("FOO");
        assertThat(response.getStatus()).isEqualTo(200);
    }

}