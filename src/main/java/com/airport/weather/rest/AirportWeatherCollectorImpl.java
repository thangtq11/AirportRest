package com.airport.weather.rest;

import com.airport.weather.data.*;
import com.google.gson.Gson;
import javaslang.collection.Seq;
import javaslang.collection.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Controller
public class AirportWeatherCollectorImpl {

    private final static Logger log = LoggerFactory.getLogger(AirportWeatherCollectorImpl.class);

    private final AirportData airportData;

    @Inject
    private Gson gson;

    @Inject
    private WeatherData weatherData;

    @Inject
    public AirportWeatherCollectorImpl(AirportData airportData) {
        this.airportData = airportData;
    }

    public Response updateWeather(String iataCode,
                                  String pointType, String datapointJson) {

        if (airportData.getAirport(iataCode).isEmpty()) {
            log.debug("updateWeather({}, {}, {}) not found", iataCode, pointType, datapointJson);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        log.debug("updateWeather({}, {}, {})", iataCode, pointType, datapointJson);

        final DataPointType dataPointType = DataPointType.valueOf(pointType.toUpperCase());
        weatherData.update(iataCode, dataPointType, gson.fromJson(datapointJson, DataPoint.class));

        return Response.status(Response.Status.OK).build();
    }

    public Response getAirport(String iataCode) {
        log.debug("getAirport({})", iataCode);
        return airportData.getAirport(iataCode)
                .map(airport ->
                        Response.status(Response.Status.OK)
                                .entity(airport)
                                .build()
                ).getOrElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    public Response addAirport(String iataCode,
                               String latitudeString,
                               String longitudeString) {
        if (airportData.getAirport(iataCode).isDefined()) {
            log.debug("addAirport({}, {}, {}) already exists", iataCode, latitudeString, longitudeString);
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        log.debug("addAirport({}, {}, {})", iataCode, latitudeString, longitudeString);

        final Double longitude = Double.valueOf(longitudeString);
        final Double latitude = Double.valueOf(latitudeString);
        airportData.addAirport(iataCode, latitude, longitude);

        return Response.status(Response.Status.OK).build();
    }

    public Response deleteAirport(String iataCode) {
        if (airportData.getAirport(iataCode).isEmpty()) {
            log.debug("addAirport({}) not exists", iataCode);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        log.debug("deleteAirport({})", iataCode);

        airportData.removeAirport(iataCode);
        weatherData.removeStation(iataCode);

        return Response.status(Response.Status.OK).build();
    }

    /**
     * Return a list of known airports as a json formatted list
     *
     * @return HTTP Response code and a json formatted list of IATA codes
     */
    public Response getAirports() {
        log.debug("getAirports()");
        final Seq<Airport> airports = airportData.getAirports();
        final Set<String> iataCodes = airports.map(Airport::iataCode).toSet();
        return Response.status(Response.Status.OK).entity(iataCodes.toJavaList()).build();
    }

    /**
     * Testing support methods
     *
     * @return HTTP Response code OK
     */
    public Response initTestData() {
        log.info("init data for testing mode");
        airportData.initTestData();
        return Response.status(Response.Status.OK).build();
    }

}
