package com.airport.weather.rest;

import com.airport.weather.data.Airport;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AirportWeatherCollectorImplIntegrationTest {

    @Inject
    private AirportWeatherCollectorImpl airportResource;


    @Inject
    private Gson gson;

    @Test
    public void queryingANonExistentAirportReturns404() throws Exception {
        Response response = airportResource.getAirport("FOO");
        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void queryingExistingAirportReturnsData() {
        airportResource.addAirport("FOO", "49", "11");

        Response response = airportResource.getAirport("FOO");
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getEntity()).isNotNull().isInstanceOf(Airport.class);

        Airport airport = (Airport) response.getEntity();
        assertThat(airport.iataCode()).isEqualTo("FOO");
        assertThat(airport.latitude()).isEqualTo(49.0);
        assertThat(airport.longitude()).isEqualTo(11);
        response=airportResource.deleteAirport("FOO");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void deletingExistingAirportShouldWork() {
        airportResource.addAirport("FOO", "49", "11");

        airportResource.deleteAirport("FOO");

        Response response = airportResource.getAirport("FOO");
        assertThat(response.getStatus()).isEqualTo(404);
    }

}