package com.airport.weather.data;

import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AirportDataTest {

    @InjectMocks
    private AirportData airportData;



    @Test
    public void canReturnEmptyResultIfAirportIsNotFound() {
        assertThat(airportData.getAirport("foo")).isEmpty();
    }

    @Test
    public void anAddedAirportCanBePersisted() {
        airportData.addAirport("foo", 49.0, 11.0);

        assertThat(airportData.getAirport("foo")).isNotEmpty().have(new Condition<Airport>() {
            @Override
            public boolean matches(Airport airport) {
                return airport.iataCode().equals("foo") &&
                        airport.latitude() == 49.0 &&
                        airport.longitude() == 11.0;
            }
        });

        airportData.removeAirport("foo");
    }


    @Test
    public void removedAirportShouldDisappearFromRepository() {
        airportData.addAirport("foo", 49.0, 11.0);

        airportData.removeAirport("foo");

        assertThat(airportData.getAirport("foo")).isEmpty();
    }

}