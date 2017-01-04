package com.airport.weather.rest;

import com.airport.weather.data.WeatherData;
import com.airport.weather.Counter;
import com.airport.weather.Utils;
import com.airport.weather.data.Airport;
import com.airport.weather.data.AirportData;
import com.airport.weather.data.Atmospheric;
import javaslang.collection.List;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Component
class AirportWeatherQueryImpl {

    private static final int MILLISECONDS_PER_DAY = 86400000;
    /** storing airport information.*/
    @Inject
    private AirportData airportData;
    /** storing weather information it's mapping with the airport data
     *  by iata code.
     */
    @Inject
    private WeatherData weatherData;
    /**
     * Internal performance counter to better understand most requested information, but will pull it off using a REST request
     * and aggregate with otherperformance metrics {@link #ping()}
     */
    @Inject
    private Counter<Airport> requestFrequency;
    @Inject
    private Counter<Double> radiusFrequency;

    public Map<String, Object> ping() {
        Map<String, Object> result = new HashMap<>();

        result.put("datasize", getCountOfDataUpdated());

        result.put("iata_freq", getAirportDataFractions());

        result.put("radius_freq", getRadiusFrequency());

        return result;
    }

    public List<Atmospheric> weather(String iataCode, double radius) {
        updateRequestFrequency(iataCode, radius);
        if (radius == 0) {
            return weatherData
                    .getWeatherDataFor(iataCode)
                    .toList();
        } else {
            return airportData
                    .getAirport(iataCode)
                    .toList()
                    .flatMap(centerAirport -> airportData
                            .getAirports()
                            .filter(otherAirport -> Utils
                                    .calculateDistance(otherAirport, centerAirport) <= radius)
                            .map(Airport::iataCode)
                            .flatMap(weatherData::getWeatherDataFor)
                            .toList());
        }
    }

    /**
     * counting weather data which are updated since a day ago.
     * @return number of the objects
     */
    private int getCountOfDataUpdated() {
        final long oneDayAgo = Utils.getCurrentTimestamp() - MILLISECONDS_PER_DAY;

        return weatherData
                .getWeatherData()
                .count(data -> data.lastUpdateTime() > oneDayAgo);
    }

    /**
     * getting airport data fractions
     * @return a map storing frequency of each airport requests.
     */
    private Map<String, Double> getAirportDataFractions() {
        Map<String, Double> freq = new HashMap<>();
        airportData.getAirports().forEach(airport ->
                freq.put(airport.iataCode(), requestFrequency.fractionOf(airport))
        );
        return freq;
    }

    /**
     * get total of requests to the server
     * @return a number of total requests.
     */
    private int[] getRadiusFrequency() {
        int maxRange = radiusFrequency.events().max().getOrElse(1000.0).intValue();
        int binSize = 10;

        final int binCount = maxRange / binSize + 1;

        int[] hist = new int[binCount];

        radiusFrequency
                .stream()
                .forEach(tuple -> {
                            final Double radius = tuple._1();
                            int binIndex = radius.intValue() / 10;

                            final int radiusFrequency = tuple._2().get();
                            hist[binIndex] += radiusFrequency;
                        }
                );

        return hist;
    }

    /**
     * update frequency of the request.
     * @param iataCode iata code
     * @param radius radius
     */
    private void updateRequestFrequency(String iataCode, Double radius) {
        airportData
                .getAirport(iataCode)
                .forEach(airport -> {
                            requestFrequency.increment(airport);
                            radiusFrequency.increment(radius);
                        }
                );
    }

}
