package com.airport.weather;

import com.airport.weather.rest.AirportWeatherCollectorImpl;
import com.airport.weather.rest.AirportWeatherQuery;
import com.airport.weather.rest.AirportWeatherCollector;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * DO NOT CHANGE THIS CLASS.
 * <p>
 * These unit tests validate that the candidate hasn't change key interfaces before submission. If this class failes to compile
 * or asserts at runtime, the submission will not be scored.
 * <p>
 * To avoid creating a submission that can not be graded, don't change this file and be sure to run mvn test before submission.
 */
public class DoNotChangeTest {

    /**
     * required group id
     */
    private static final String POM_GROUP_ID = "com.airport.trial";

    /**
     * required artifact id
     */
    private static final String POM_ARTIFACT_ID = "weather";

    /**
     * required version number
     */
    private static final String POM_VERSION = "1.2.0";

    private AirportWeatherCollector collector;

    private AirportWeatherCollectorImpl collectorAirport;

    private AirportWeatherQuery queryEndpoint;


    /**
     * The compile time interface validator for WeatherCollector. This method will NEVER been called,
     * but must compile.
     */
    private void validateInterfaceWeatherCollector() {

        //
        // do not edit this method, if you have changed the weather collector interface and this
        // class does not compile, you should reverse your API changes.
        //

        Response pingResp = collector.ping();

        Response updateWeatherResp = collector.updateWeather(null, null, null);

        Response getAirportsResp = collector.getAirports();

        Class<AirportWeatherCollectorImpl> resourceClass = collector.collectorAirportSubResource();
    }

    /**
     * The compile time interface validator for WeatherCollector. This method will NEVER been called,
     * but must compile.
     */
    private void validateInterfaceAirportCollector() {

        //
        // do not edit this method, if you have changed the weather collector interface and this
        // class does not compile, you should reverse your API changes.
        //

        Response getAirportResp = collectorAirport.getAirport(null);

        Response addAirportResp = collectorAirport.addAirport(null, null, null);

        Response deleteAirportResp = collectorAirport.deleteAirport(null);
    }

    /**
     * The compile time interface validator for WeatherQueryEndpoint. This method will NEVER been called,
     * but must compile.
     */
    private void validateInterfaceWeatherQueryEndpoint() {

        //
        // do not change this method - if you have changed the query endpoint in a way that prevents
        // this class from compiling, you should revert your changes
        //

        String pingResp = queryEndpoint.ping();

        Response getResp = queryEndpoint.weather(null, null);
    }

    @Test
    public void testPomFile() throws Exception {
        // Locate the path of pom.xml with standard maven folder structure
        Path testClassPath = Paths.get(DoNotChangeTest.class.getClassLoader().getResource("").toURI());
        Path pomPath = testClassPath.getParent().getParent().resolve("pom.xml");
        File pomFile = pomPath.toFile();

        // Check pom.xml file existing
        Assert.assertTrue(pomFile.exists());
        Assert.assertTrue(pomFile.isFile());

        // Load the pom.xml and find the groupId, artifactId and version.
        String groupdId = "";
        String artifactId = "";
        String version = "";
        Document d = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new FileInputStream(pomFile));
        Node root = d.getChildNodes().item(0);
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node tmp = children.item(i);
            if (tmp.getNodeName().equalsIgnoreCase("groupId")) {
                groupdId = tmp.getTextContent().trim();
            } else if (tmp.getNodeName().equalsIgnoreCase("artifactId")) {
                artifactId = tmp.getTextContent().trim();
            } else if (tmp.getNodeName().equalsIgnoreCase("version")) {
                version = tmp.getTextContent().trim();
            }
        }

        // Assert pom attributes
        Assert.assertEquals("Do not change the groupId in pom.xml", POM_GROUP_ID, groupdId);
        Assert.assertEquals("Do not change the artifactId in pom.xml", POM_ARTIFACT_ID, artifactId);
        Assert.assertEquals("Do not change the version in pom.xml", POM_VERSION, version);
    }

}
