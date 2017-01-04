package com.airport.weather.data;

import javaslang.collection.List;
import javaslang.collection.Seq;
import javaslang.control.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Storing airport data in to a concurrent hash map for thread safe.
 * Keys are iata codes. They are unique. This is a memory database for holding airport data
 */
@Component
public class AirportData {
    private final static Logger log = LoggerFactory.getLogger(AirportData.class);
    /** all known airports */
    private final static ConcurrentHashMap<String, Airport> airportsByIataCode = new ConcurrentHashMap<>();

    /**
     * adding an airport object to the database
     * @param airport input airport object
     */
    public void addAirport(Airport airport) {
        log.debug(" adding an airport:" + airport + " mapSize:" + airportsByIataCode.size());
        airportsByIataCode.put(airport.iataCode(), airport);
    }
    /**
     * searching an airport by iata code
     * @param iataCode uinque iata code
     */
    public Option<Airport> getAirport(String iataCode) {
        return Option.of(airportsByIataCode.get(iataCode));
    }
    /**
     * getting all airport data
     */
    public Seq<Airport> getAirports() {
        return List.ofAll(airportsByIataCode.values()).toStream();
    }
    /**
     * removing an airport by iata code
     * @param iataCode uinque iata code
     */
    public void removeAirport(String iataCode) {
        airportsByIataCode.remove(iataCode);
    }
    /**
     * adding an airport object to the database by following params
     * @param iataCode        the 3 letter airport code of the new airport
     * @param latitude  the airport's latitude in degrees as a string [-90, 90]
     * @param longitude the airport's longitude in degrees as a string [-180, 180]
     */
    public void addAirport(String iataCode, Double latitude, Double longitude) {
        final ImmutableAirport airport = ImmutableAirport.builder()
                .iataCode(iataCode)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        addAirport(airport);
    }
    /**
     * A dummy init method that loads hard coded airport data for testing purpose
     */
    public void initTestData() {
        addAirport("BOS", -71.005181, 42.364347);
        addAirport("EWR", -74.168667, 40.6925);
        addAirport("JFK", -73.778925, 40.639751);
        addAirport("LGA", -73.872608, 40.777245);
        addAirport("MMU", -74.4148747, 40.79935);
    }
}
