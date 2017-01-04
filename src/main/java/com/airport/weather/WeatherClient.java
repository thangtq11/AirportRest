package com.airport.weather;

import com.airport.weather.data.Airport;
import com.airport.weather.data.DataPoint;
import com.airport.weather.data.ImmutableDataPoint;
import com.airport.weather.data.ImmutableAirport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ServiceLoader;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A reference implementation for the weather client.
 * Consumers of the REST API can look at WeatherClient to understand API semantics.
 * This existing client populates the REST endpoint with dummy datatype
 * useful for testing.
 *
 * @author code test administrator
 */
public class WeatherClient {

    private final static Logger log = LoggerFactory.getLogger(WeatherClient.class);
    private static final String BASE_URI = "http://localhost:9090";
    private final Gson gson;
    /**
     * end point for read queries
     */
    private WebTarget query;
    /**
     * end point to supply updates
     */
    private WebTarget collect;
    /**
     * statistics success and error reponses from the server
     */
    private AtomicInteger numSessions200OK;
    private AtomicInteger numSessionsErr;

    public WeatherClient() {
        Client client = ClientBuilder.newClient();
        gson = gsonBuilder();
        query = client.target(BASE_URI + "/query");
        collect = client.target(BASE_URI + "/collect");
        numSessions200OK = new AtomicInteger(0);
        numSessionsErr = new AtomicInteger(0);
    }

    private static Gson gsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        for (TypeAdapterFactory factory : ServiceLoader.load(TypeAdapterFactory.class)) {
            gsonBuilder.registerTypeAdapterFactory(factory);
        }
        return gsonBuilder.create();
    }

    public static void usage() {
        log.error(" arguments are not a valid input");
        log.error(" please usage:  WeatherClient [url] [an uploading file] [1 : enable stress test , 0 disable]");
        log.error(" exp: WeatherClient http://localhost:9090 ./CrossOWeather/db/lite_airports.dat 1");
        System.exit(1);
    }

    /**
     * for implementing a black box test function.
     * leveling of test is auto grade with
     * one session -> multi sessions -> stress test
     * @param args
     */
    public static void main(String[] args) {
        try {

            if (args == null || args.length <= 1 || args[0] == null || args[0].isEmpty() || args[1] == null || args[1].isEmpty()
                    || args[2] == null || args[2].isEmpty()) {
                usage();
            }
            WeatherClient wc = new WeatherClient();
            wc.testOneSession();
            wc.simpleTest();
            if (Integer.parseInt(args[2]) == 1)
                wc.simpleStressTest(args);
            log.info("complete");
            System.exit(0);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            usage();
        }
    }

    /**
     * checking controller endpoint to know whether the server alive or not
     *
     * @return response from the server
     */
    public Response pingCollect() {
        WebTarget path = collect.path("/ping");
        Response response = path.request().get();
        log.info("collect.ping: " + response.readEntity(String.class) + "\n");
        return response;
    }

    /**
     * checking query endpoint to know whether the server alive or not
     *
     * @return response from the server
     */
    public Response pingQuery() {
        WebTarget path = query.path("/ping");
        Response response = path.request().get();
        log.info("query.ping: " + response.readEntity(String.class));
        return response;
    }

    /**
     * A simple init method that loads hard coded airport data for testing purpose
     */
    public Response initDataTest() {
        WebTarget path = collect.path("/test");
        Response response = path.request().get();
        log.info("collect.test: initDataTest: " + response.readEntity(String.class) + "\n");
        return response;
    }

    /**
     * querying weather information of an airport.
     *
     * @param iata         the three letter airport code
     * @param radiusString the radius, in km, from which to collect weather datatype
     */
    public Response queryWeather(String iata, String radiusString) {
        WebTarget path = query.path("/weather/" + iata + "/" + radiusString);
        Response response = path.request().get();
        log.info("queryWeather." + iata + "." + radiusString + ": " + response.readEntity(String.class));
        return response;
    }

    /**
     * testing Add a new airport to the known airport list.
     *
     * @param iataCode        the 3 letter airport code of the new airport
     * @param latitudeString  the airport's latitude in degrees as a string [-90, 90]
     * @param longitudeString the airport's longitude in degrees as a string [-180, 180]
     * @return HTTP Response code for the add operation
     */
    public Response createAirport(String iataCode, double latitudeString, double longitudeString) {
        WebTarget path = collect.path("/airport/" + iataCode + "/" + latitudeString + "/" + longitudeString);
        Airport airport = ImmutableAirport.builder().iataCode(iataCode).latitude(latitudeString).longitude(longitudeString).build();
        Response response = path.request().post(Entity.entity(gson.toJson(airport), "application/json"));
        log.info("collect.createAirport: " + iataCode + response.readEntity(String.class) + " status:" + response.getStatus());
        return response;
    }

    /**
     * testing Add a new airport to the known airport list.
     *
     * @param iataCode        the 3 letter airport code of the new airport
     * @param latitudeString  the airport's latitude in degrees as a string [-90, 90]
     * @param longitudeString the airport's longitude in degrees as a string [-180, 180]
     * @return HTTP Response code for the add operation
     */
    public Airport createAnAirport(String iataCode, double latitudeString, double longitudeString) {
        Airport airport = ImmutableAirport.builder().iataCode(iataCode).latitude(latitudeString).longitude(longitudeString).build();
        return airport;
    }

    /**
     * querying an airport with iata code
     *
     * @param iataCode
     * @return response from the server
     */
    public Response queryAirport(String iataCode) {
        WebTarget path = collect.path("/airport/" + iataCode);
        Response response = path.request().get();
        log.info("collect.queryAirport: " + response.readEntity(String.class) + " status:" + response.getStatus());
        return response;
    }

    /**
     * delete an airport with iata code
     *
     * @param iataCode the 3 letter airport code of the airport
     * @return response from the server
     */
    public Response deleteAirport(String iataCode) {
        WebTarget path = collect.path("/airport/" + iataCode);
        Airport airport = ImmutableAirport.builder().iataCode(iataCode).latitude(0).longitude(0).build();
        Response response = path.request().delete();
        log.info("collect.deleteAirport: " + iataCode + response.readEntity(String.class) + " status:" + response.getStatus());
        return response;
    }

    /**
     * update weather of an airport with information from a collected point
     *
     * @param iataCode
     * @param pointType on the six Atmospheric factors
     * @param first     1st quartile -- useful as a lower bound
     * @param last      3rd quartile value -- less noisy upper value
     * @param mean      the mean of the observations
     * @param median    2nd quartile -- median value
     * @param count     the total number of measurements
     * @return
     */
    public Response updateAirportWeather(String iataCode, String pointType, int first, int last, int mean, int median, int count) {
        WebTarget path = collect.path("/weather/" + iataCode + "/" + pointType);
        DataPoint data = ImmutableDataPoint.builder()
                .first(first).last(last).mean(mean).median(median).count(count)
                .build();
        Response response = path.request().post(Entity.entity(gson.toJson(data), "application/json"));
        log.info("collect.updateAirportWeather: " + iataCode + response.readEntity(String.class) + " status:" + response.getStatus());
        return response;
    }

    /**
     * A simple test to validate functions of the REST server
     */
    public void simpleTest() {
        try {
            this.pingCollect();
            this.initDataTest();
            //create airport
            this.createAirport("LPL", 49.0, 11.0);
            //update BOS with pointType "wind"
            this.updateAirportWeather("BOS", "wind", 0, 10, 6, 4, 20);
            this.queryWeather("BOS", "100");
            this.queryWeather("JFK", "20");
            this.queryWeather("EWR", "50");
            this.queryWeather("LGA", "40");
            this.queryWeather("MMU", "1000");
            this.queryWeather("LPL", "200");
            this.queryAirport("MMU");
            this.queryAirport("BOS");
            this.queryAirport("EWR");
            this.queryAirport("JFK");
            this.queryAirport("LGA");
            this.queryAirport("LPL");
            this.pingQuery();
            this.deleteAirport("BOS");
            this.deleteAirport("EWR");
            this.deleteAirport("JFK");
            this.deleteAirport("LGA");
            this.deleteAirport("MMU");
            this.deleteAirport("LPL");
            this.pingQuery();
            //it'll be shutdown the server , be careful with this function
            //wc.exit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * A simple stress test, it'll load data from a file and making a stress test to the REST server. A statistics function will be printing to evaluate the response results from the server.
     *
     * @param args[] [server url address] [a full path data file for uploading to the server]
     */
    public void simpleStressTest(String[] args) {
        try {
            LocalDateTime start = LocalDateTime.now();
            File airportDataFile = new File(args[1]);
            if (!airportDataFile.exists() || airportDataFile.length() == 0) {
                log.error(airportDataFile + " is not a valid input");
                System.exit(1);
            }
            AirportLoader al = new AirportLoader(args[0]);
            al.uploadData(airportDataFile);
            //
            al.getAirportData().getAirports().forEach(Airport -> testRestSession(Airport));
            al.printStatistics();
            //stop time
            Duration duration = Duration.between(start, LocalDateTime.now());
            log.info("number of 200 OK sessions:" + numSessions200OK.get());
            log.info("number of error sessions:" + numSessionsErr.get());
            log.info("started time:" + start);
            log.info("stopped time:" + LocalDateTime.now());
            log.info("duration:" + duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase());
            log.info("number of success Sessions per second:" + numSessions200OK.get() / duration.getSeconds());
            log.info("number of error Sessions per second:" + numSessionsErr.get() / duration.getSeconds());
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }

    }

    /**
     * test a session , it's combined of create, update query and delete transactions
     * @param airport airport object pass through
     */
    public void testRestSession(Airport airport) {
        try {
            //create airport
            Response createRes = createAirport(airport.iataCode(), airport.latitude(), airport.longitude());
            Response updateRes = updateAirportWeather(airport.iataCode(), "wind", 0, 10, 6, 4, 20);
            Response queryAirRes = queryAirport(airport.iataCode());
            Response queryWeaRes = queryWeather(airport.iataCode(), "10");
            //
            if (createRes.getStatus() == 200 &&
                    updateRes.getStatus() == 200 &&
                    queryAirRes.getStatus() == 200 &&
                    queryWeaRes.getStatus() == 200) {
                numSessions200OK.incrementAndGet();
                return;
            }
            numSessionsErr.incrementAndGet();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
    }

    /**
     * test a session , it's combined of create, update query and delete transactions
     */
    public void testOneSession() {
        try {
            //create airport
            Airport airport = createAnAirport("APK", 49.0, 11.0);
            testRestSession(airport);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
        }
    }
    /**
     * shut down the server, be carefull with this function
     */
    public void exit() {
        try {
            collect.path("/exit").request().get();
        } catch (Throwable t) {
            // swallow
        }
    }

}
