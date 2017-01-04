package com.airport.weather.data;

/**
 * determining location of airport
 */
public interface Point {
    /**
     * Decimal degrees, usually to six significant digits.
     * Negative is South, positive is North
     */
    double latitude();

    /**
     * Decimal degrees, usually to six significant digits.
     * Negative is West, positive is East
     */
    double longitude();
}
