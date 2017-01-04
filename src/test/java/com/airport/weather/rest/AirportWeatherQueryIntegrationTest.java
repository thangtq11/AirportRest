package com.airport.weather.rest;

import com.airport.weather.data.ImmutableDataPoint;
import com.airport.weather.data.Atmospheric;
import com.airport.weather.data.DataPoint;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AirportWeatherQueryIntegrationTest {

    @Inject
    private AirportWeatherQuery query;

    @Inject
    private AirportWeatherCollector update;

    @Inject
    private Gson _gson;

    private ImmutableDataPoint dataPoint;

    @Before
    public void setUp() throws Exception {
        dataPoint = ImmutableDataPoint.builder()
                .count(10).first(10).median(20).last(30).mean(22).build();
        update.initTest();
        update.updateWeather("BOS", "wind", _gson.toJson(dataPoint));
        query.weather("BOS", "0").getEntity();
    }

    @Test
    public void pingCanReturnDatasizeAndIataFreqInformation() throws Exception {
        String ping = query.ping();
        JsonElement pingResult = new JsonParser().parse(ping);
        assertThat(pingResult.getAsJsonObject().get("datasize").getAsInt()).isEqualTo(1);
        assertThat(pingResult.getAsJsonObject().get("iata_freq").getAsJsonObject().entrySet()).hasSize(5);
    }

    @Test
    public void weatherQueryCanReturnPreviouslyUploadedData() throws Exception {
        List<Atmospheric> ais = (List<Atmospheric>) query.weather("BOS", "0").getEntity();
        assertThat(ais.get(0).wind()).isEqualTo(dataPoint);
    }

    @Test
    public void weatherQueryWithIncludingNearbyRadiusCanReturnMultipleResults() throws Exception {
        // check datasize response
        Response response= update.updateWeather("JFK", "wind", _gson.toJson(dataPoint));
        assertThat(response.getStatus()).isEqualTo(200);

        dataPoint = dataPoint.withMean(40);
        update.updateWeather("EWR", "wind", _gson.toJson(dataPoint));
        dataPoint = dataPoint.withMean(30);
        update.updateWeather("LGA", "wind", _gson.toJson(dataPoint));

        List<Atmospheric> ais = (List<Atmospheric>) query.weather("JFK", "200").getEntity();
        assertThat(ais).hasSize(3);
    }

    @Test
    public void consecutiveWeatherUpdatesOfDifferentTypeCanBeAccumulated() throws Exception {

        DataPoint windDp = ImmutableDataPoint.builder()
                .count(10).first(10).median(20).last(30).mean(22).build();
        update.updateWeather("BOS", "wind", _gson.toJson(windDp));
        query.weather("BOS", "0").getEntity();

        String ping = query.ping();
        JsonElement pingResult = new JsonParser().parse(ping);
        assertThat(pingResult.getAsJsonObject().get("datasize").getAsInt()).isEqualTo(1);

        DataPoint cloudCoverDp = ImmutableDataPoint.builder()
                .count(4).first(10).median(60).last(100).mean(50).build();
        update.updateWeather("BOS", "cloudcover", _gson.toJson(cloudCoverDp));

        List<Atmospheric> ais = (List<Atmospheric>) query.weather("BOS", "0").getEntity();
        assertThat(ais.get(0).wind()).isEqualTo(windDp);
        assertThat(ais.get(0).cloudCover()).isEqualTo(cloudCoverDp);
    }

}