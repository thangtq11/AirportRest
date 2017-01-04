package com.airport.weather;


import com.airport.weather.data.AirportData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple airport loader which reads a file from disk and sends entries to the webservice
 * <p>
 * TODO: Implement the Airport Loader
 *
 * @author code test administrator
 */
public class AirportLoader {

    private final static Logger log = LoggerFactory.getLogger(AirportLoader.class);
    /**
     * end point for read queries
     */
    private WebTarget query;
    /**
     * end point to supply updates
     */
    private WebTarget collect;
    /**
     * for statistics functions when loading data airport data from a file
     */
    private AtomicInteger numAirports;
    private AtomicInteger numErrAirports;
    private AtomicInteger numLines;
    private AtomicInteger numErrLines;
    private AirportData airportData;

    /**
     * initializing AirportLoader by an uploading url
     *
     * @param baseUrl url address of the server for uploading data.
     */
    public AirportLoader(String baseUrl) {
        Client client = ClientBuilder.newClient();
        collect = client.target(baseUrl + "/collect");
        numAirports = new AtomicInteger(0);
        numErrAirports = new AtomicInteger(0);
        numLines = new AtomicInteger(0);
        numErrLines = new AtomicInteger(0);
        airportData = new AirportData();
    }

    /**
     * main method for loading data from a file and uploading the data to a server which is specified by url address.
     *
     * @param args [url] [a upload file]
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        if (args == null || args.length <= 1 || args[0] == null || args[0].isEmpty()
                || args[1] == null || args[1].isEmpty()) {
            log.error(" arguments are not a valid input");
            log.error(" please usage:  AirportLoader [url] [a uploading file]");
            log.error(" exp: AirportLoader http://localhost:9090 ./CrossOWeather/db/lite_airports.dat");
            System.exit(1);
        }
        File airportDataFile = new File(args[1]);
        if (!airportDataFile.exists() || airportDataFile.length() == 0) {
            log.error(airportDataFile + " is not a valid input");
            System.exit(1);
        }
        AirportLoader al = new AirportLoader(args[0]);
        al.upload(airportDataFile);
        al.printStatistics();
        System.exit(0);
    }

    /**
     * uploading a specified file, the file must be existed before upload to the server
     *
     * @param airportDataFile a full path file
     * @throws IOException
     */
    public void upload(File airportDataFile) throws IOException {
        try (InputStream airportDataStream = new FileInputStream(airportDataFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(airportDataStream, "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numLines.incrementAndGet();
                processLine(line);
            }
        }
    }

    /**
     * uploading a specified file, the file must be existed before upload to the server
     *
     * @param airportDataFile a full path file
     * @throws IOException
     */
    public void uploadData(File airportDataFile) throws IOException {
        try (InputStream airportDataStream = new FileInputStream(airportDataFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(airportDataStream, "UTF-8"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numLines.incrementAndGet();
                processAirportData(line);
            }
        }
    }

    /**
     * getting the memory uploaded data
     *
     * @return a map which stored data
     */
    public AirportData getAirportData() {
        return airportData;
    }

    /**
     * processing a line to extract valid airport data
     *
     * @param line it's must be validated before pass to this function.
     */
    private void processLine(String line) {
        String[] columns = Utils.split(line);
        if (columns.length <= 7) {
            log.error(" when processLine line :" + line);
            numErrLines.incrementAndGet();
            return;
        }
        String iataCode = extractIataCode(columns);
        String path = extractRequestPath(columns);
        if (path == null) {
            numErrLines.incrementAndGet();
            log.error(" when processLine line :" + line);
            return;
        }

        Response post = collect.path(path).request().post(Entity.text(""));
        log.info(" getUri :" + collect.getUri() + path);
        processResultStatus(iataCode, post);
    }

    /**
     * processing the line to get an airport object, the object will be inserted in to the map.
     *
     * @param line it's must be validated before pass to this function.
     */
    private void processAirportData(String line) {
        String[] columns = Utils.split(line);
        if (columns.length <= 7) {
            log.error(" when processLine line :" + line);
            numErrLines.incrementAndGet();
            return;
        }
        String iataCode = extractIataCode(columns);
        String latitude = columns[6];
        String longitude = columns[7];
        if (iataCode.isEmpty() || iataCode.trim().isEmpty()
                || latitude.isEmpty() || latitude.trim().isEmpty()
                || longitude.isEmpty() || longitude.trim().isEmpty()
                ) {
            numErrLines.incrementAndGet();
            log.error(" when processLine line :" + line);
            numErrAirports.incrementAndGet();
            return;
        }
        airportData.addAirport(iataCode, Double.parseDouble(latitude), Double.parseDouble(longitude));
        numAirports.incrementAndGet();
    }

    /**
     * extracting iata code
     *
     * @param columns iata string
     * @return iata string code
     */
    private String extractIataCode(String[] columns) {
        return columns[4].replace("\"", "");
    }

    /**
     * generating path for uploading airport data to the server
     *
     * @param columns string with airport information columns
     * @return path
     */
    private String extractRequestPath(String[] columns) {
        String iataCode = extractIataCode(columns);
        String latitude = columns[6];
        String longitude = columns[7];
        if (iataCode.isEmpty() || iataCode.trim().isEmpty()
                || latitude.isEmpty() || latitude.trim().isEmpty()
                || longitude.isEmpty() || longitude.trim().isEmpty()
                ) {
            return null;
        }
        return "/airport/" + iataCode + "/" + latitude + "/" + longitude;
    }

    /**
     * processing response results from the server and updating statistics information
     *
     * @param iataCode iata code
     * @param post     response of server
     */
    private void processResultStatus(String iataCode, Response post) {
        switch (post.getStatus()) {
            case 200:
                log.info("Added successfull an airport '" + iataCode + "': " + post.getStatus() + " " + post.getStatusInfo());
                numAirports.incrementAndGet();
                break;

            case 403:
                log.warn("airport entry '" + iataCode + "' already exists");
                numErrAirports.incrementAndGet();
                break;

            default:
                numErrAirports.incrementAndGet();
                log.error("ERROR when adding airport '" + iataCode + "': " + post.getStatus() + " " + post.getStatusInfo());
        }
    }

    /**
     * print statistics information
     */
    public void printStatistics() {
        log.info("===============================================");
        log.info("===============================================");
        log.info("statistics information");
        log.info("total lines :" + numLines.get());
        log.info("number of error lines :" + numErrLines.get());
        log.info("number of success airports :" + numAirports.get());
        log.info("number of error airports :" + numErrAirports.get());
    }
}
