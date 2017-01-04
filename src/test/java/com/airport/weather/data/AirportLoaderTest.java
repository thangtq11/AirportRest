package com.airport.weather.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

/**
 * A simple airport loader which reads a file from disk and sends entries to the webservice
 *
 * TODO: Testing the Airport Loader
 *
 * @author code test administrator
 */
@RunWith(MockitoJUnitRunner.class)
public class AirportLoaderTest{

    @Test
    public  void test() throws IOException {
//        File airportDataFile = new File(args[0]);
//        if (!airportDataFile.exists() || airportDataFile.length() == 0) {
//            System.err.println(airportDataFile + " is not a valid input");
//            System.exit(1);
//        }
//        AirportLoader al = new AirportLoader("http://localhost:9090");
//        al.upload(airportDataFile);
    }
}
