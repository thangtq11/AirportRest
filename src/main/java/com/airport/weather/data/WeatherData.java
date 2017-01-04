package com.airport.weather.data;

import javaslang.collection.Seq;
import javaslang.collection.Stream;
import javaslang.control.Option;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.concurrent.ConcurrentHashMap;

/**
 * storing weather information of all airports in the AirportData.
 * The information of each weather is mapping with the airport by iata code.
 * Six Factors That Determine Weather
 *
 */
@Component
public class WeatherData {
    /** an in-memory database for storing weather of airports */
    private final ConcurrentHashMap<String, AtmosphericInformation> stationDataRepositoryByStationId = new ConcurrentHashMap<>();

    @Inject
    private Provider<AtmosphericInformation> stationDataRepositoryProvider;

    /**
     * Update datatype for station with stationId, creates a new {@link AtmosphericInformation} if {@code stationId} is not known.
     *
     * @param stationId id which is used to reference the station , using iata code for mapping with the airport
     * @param dataType  type of datatype
     * @param data      datatype to be updated
     */
    public void update(String stationId, DataPointType dataType, DataPoint data) {
        stationDataRepositoryByStationId
                .computeIfAbsent(stationId, k -> stationDataRepositoryProvider.get())
                .update(dataType, data);
    }

    /**
     * get all weather information
     * @return
     */
    public Seq<Atmospheric> getWeatherData() {
        return Stream.ofAll(stationDataRepositoryByStationId.values())
                .map(AtmosphericInformation::toData);
    }

    /**
     * get weather of an specific airport
     * @param stationId iata code of the airport
     * @return atmospheric of the airport
     */
    public Option<Atmospheric> getWeatherDataFor(String stationId) {
        return Option.of(stationDataRepositoryByStationId.get(stationId))
                .map(AtmosphericInformation::toData);
    }

    /**
     * remove weather of an specific airport
     * @param stationId iata code of the airport
     */
    public void removeStation(String stationId) {
        stationDataRepositoryByStationId.remove(stationId);
    }
}
