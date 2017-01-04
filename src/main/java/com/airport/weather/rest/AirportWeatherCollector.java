package com.airport.weather.rest;

import com.airport.weather.data.Airport;
import com.airport.weather.data.DataPointType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * A REST implementation of the WeatherCollector API. Accessible only to airport
 * weather collection sites via secure VPN.
 */

@Path(Paths.COLLECT)
@Controller
public class AirportWeatherCollector {
    private final static Logger log = LoggerFactory.getLogger(AirportWeatherCollector.class);

    @Inject
    private AirportWeatherCollectorImpl implement;

    /**
     * A liveliness check for the collection endpoint.
     *
     * @return 200 OK if the endpoint is alive functioning
     */
    @GET
    @Path(Paths.PING)
    public Response ping() {
        return Response.status(Response.Status.OK).entity("ready").build();
    }

    /**
     * Update the airports atmospheric information for a particular pointType with
     * json formatted datatype point information.
     *
     * @param iataCode      the 3 letter airport code
     * @param pointType     the point type, {@link DataPointType} for a complete list
     * @param datapointJson a json dict containing mean, first, second, thrid and count keys
     * @return HTTP Response code
     */
    @POST
    @Path(Paths.WEATHER + "/{" + Paths.IATA_CODE + "}/{" + Paths.POINT_TYPE + "}")
    public Response updateWeather(@PathParam(Paths.IATA_CODE) String iataCode,
                                  @PathParam(Paths.POINT_TYPE) String pointType, String datapointJson) {

        if (iataCode == null || iataCode.isEmpty() || pointType == null || pointType.isEmpty() || datapointJson == null || datapointJson.isEmpty())
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return implement.updateWeather(iataCode, pointType, datapointJson);
    }

    /**
     * Retrieve airport datatype, including latitude and longitude for a particular airport
     *
     * @param iataCode the 3 letter airport code
     * @return an HTTP Response with a json representation of {@link Airport}
     */
    @GET
    @Path(Paths.AIRPORT + "/{" + Paths.IATA_CODE + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAirport(@PathParam(Paths.IATA_CODE) String iataCode) {
        log.debug("getAirport({})", iataCode);
        if (iataCode == null || iataCode.isEmpty())
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return implement.getAirport(iataCode);
    }

    /**
     * Add a new airport to the known airport list.
     *
     * @param iataCode        the 3 letter airport code of the new airport
     * @param latitudeString  the airport's latitude in degrees as a string [-90, 90]
     * @param longitudeString the airport's longitude in degrees as a string [-180, 180]
     * @return HTTP Response code for the add operation
     */
    @POST
    @Path(Paths.AIRPORT + "/{" + Paths.IATA_CODE + "}/{" + Paths.LATITUDE + "}/{" + Paths.LONGITUDE + "}")
    public Response addAirport(@PathParam(Paths.IATA_CODE) String iataCode,
                               @PathParam(Paths.LATITUDE) String latitudeString,
                               @PathParam(Paths.LONGITUDE) String longitudeString) {
        if (iataCode == null || iataCode.isEmpty() || latitudeString == null || latitudeString.isEmpty() || longitudeString == null || longitudeString.isEmpty())
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        return implement.addAirport(iataCode, latitudeString, longitudeString);
    }

    /**
     * Remove an airport from the known airport list
     *
     * @param iataCode the 3 letter airport code
     * @return HTTP Repsonse code for the delete operation
     */
    @DELETE
    @Path(Paths.AIRPORT + "/{" + Paths.IATA_CODE + "}")
    public Response deleteAirport(@PathParam(Paths.IATA_CODE) String iataCode) {
        if (iataCode == null || iataCode.isEmpty())
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        return implement.deleteAirport(iataCode);
    }


    /**
     * Return a list of known airports as a json formatted list
     *
     * @return HTTP Response code and a json formatted list of IATA codes
     */
    @GET
    @Path(Paths.AIRPORTS)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAirports() {
        log.debug("getAirports()");
        return implement.getAirports();
    }


    public Class<AirportWeatherCollectorImpl> collectorAirportSubResource() {
        log.trace("collectorAirportSubResource()");
        return AirportWeatherCollectorImpl.class;
    }

    //
    // Testing support methods
    //
    @GET
    @Path(Paths.TEST)
    public Response initTest() {
        log.info("init data for testing mode");
        return implement.initTestData();
    }

    //
    // Internal support methods
    //
    @GET
    @Path(Paths.EXIT)
    public Response exit() {
        log.info("exit() shutting down server");
        System.exit(0);
        return Response.noContent().build();
    }
}
