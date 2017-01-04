package com.airport.weather.rest;

import com.airport.weather.data.Atmospheric;
import com.google.gson.Gson;
import javaslang.collection.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * The Weather App REST endpoint allows clients to query, update and check health stats. Currently, all datatype is * held in memory. The end point deploys to a single container
 *
 * @author code test administrator
 */
@Path(Paths.QUERY)
@Component
public class AirportWeatherQuery {

    private static final Logger log = LoggerFactory.getLogger(AirportWeatherQuery.class);

    @Inject
    private Gson gson;

    @Inject
    private AirportWeatherQueryImpl implement;

    /**
     * Retrieve health and status information for the the query api. Returns information about how the number
     * of datapoints currently held in memory, the frequency of requests for each IATA code and the frequency of
     * requests for each radius.
     *
     * @return a JSON formatted dict with health information.
     */
    @GET
    @Path(Paths.PING)
    @Produces(MediaType.TEXT_PLAIN)
    public String ping() {
        log.debug("ping()");

        Map<String, Object> result = implement.ping();

        return gson.toJson(result);
    }

    /**
     * Retrieve the most up to date atmospheric information from the given airport and other airports in the given
     * radius.
     *
     * @param iataCode     the three letter airport code
     * @param radiusString the radius, in km, from which to collect weather datatype
     * @return an HTTP Response and a list of {@link Atmospheric} from the requested airport and airports in the given radius
     */
    @GET
    @Path(Paths.WEATHER + "/{" + Paths.IATA_CODE + "}/{" + Paths.RADIUS + "}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response weather(@PathParam(Paths.IATA_CODE) String iataCode,
                            @PathParam(Paths.RADIUS) String radiusString) {
        log.debug("weather({}, {})", iataCode, radiusString);

        final boolean radiusStringUnset = radiusString == null || radiusString.trim().isEmpty();
        double radius = radiusStringUnset ? 0 : Double.valueOf(radiusString);

        final List<Atmospheric> weather = implement.weather(iataCode, radius);

        return Response.status(Response.Status.OK).entity(weather.toJavaList()).build();
    }
}
