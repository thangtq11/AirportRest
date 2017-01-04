package com.airport.weather.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/**
 * encapsulates sensor information for a particular location.
 * There are six factors that determine the state and condition of the atmosphere
 * and, therefore, influence and determine the weather. They include:
 * 1. temperature
 * 2. wind
 * 3. humidity
 * 4. precipitation
 * 5. pressure
 * 6. cloudCover
 */
@Value.Immutable
@JsonSerialize(as = ImmutableAtmospheric.class)
@JsonDeserialize(as = ImmutableAtmospheric.class)
public interface Atmospheric {

    /**
     * temperature in degrees celsius
     */
    @Nullable
    DataPoint temperature();

    /**
     * wind speed in km/h
     */
    @Nullable
    DataPoint wind();

    /**
     * humidity in percent
     */
    @Nullable
    DataPoint humidity();

    /**
     * precipitation in cm
     */
    @Nullable
    DataPoint precipitation();

    /**
     * pressure in mmHg
     */
    @Nullable
    DataPoint pressure();

    /**
     * cloud cover percent from 0 - 100 (integer)
     */
    @Nullable
    DataPoint cloudCover();

    /**
     * the last time this datatype was updated, in milliseconds since UTC epoch
     */
    long lastUpdateTime();
}
