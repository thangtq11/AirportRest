package com.airport.weather.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/**
 * Basic airport information. An airport is identified by an unique IATA code,
 * a three-letter code designating many airports around the world, defined by the
 * International Air Transport Association (IATA).
 */

@Value.Immutable
@JsonSerialize(as = ImmutableAirport.class)
@JsonDeserialize(as = ImmutableAirport.class)
public interface Airport extends Point {

    @JsonProperty("iata")
    String iataCode();
}
