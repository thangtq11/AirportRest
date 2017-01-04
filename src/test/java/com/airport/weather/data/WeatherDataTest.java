package com.airport.weather.data;

import javaslang.control.Option;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataTest {

    @Mock
    private AtmosphericInformation atmosphericInformation;

    @Spy
    private Provider<AtmosphericInformation> dataProvider = new Provider<AtmosphericInformation>() {
        @Override
        public AtmosphericInformation get() {
            return atmosphericInformation;
        }
    };

    @InjectMocks
    private WeatherData weatherData;

    @Test
    public void retrievesEmptyWeatherDataAfterInitialization() {
        Assertions.assertThat(weatherData.getWeatherData().toList()).isEmpty();
    }

    @Test
    public void retrievesEmptyElementAfterInitialization() {
        Assertions.assertThat(weatherData.getWeatherDataFor("foo")).isEmpty();
    }

    @Test
    public void canCreateDataAndAddNewDataWhenUpdating() {
        DataPoint dataPoint = mock(DataPoint.class);

        weatherData.update("foo", DataPointType.WIND, dataPoint);

        assertThat(weatherData.getWeatherData().size()).isEqualTo(1);
        verify(atmosphericInformation).update(DataPointType.WIND, dataPoint);
    }

    @Test
    public void canReuseDataAndAddNewDataWhenUpdatingAnExistingRepository() {
        DataPoint dataPoint = mock(DataPoint.class);
        weatherData.update("foo", DataPointType.WIND, dataPoint);

        weatherData.update("foo", DataPointType.HUMIDITY, dataPoint);

        assertThat(weatherData.getWeatherData().size()).isEqualTo(1);
        verify(dataProvider).get();
        verify(atmosphericInformation).update(DataPointType.WIND, dataPoint);
        verify(atmosphericInformation).update(DataPointType.HUMIDITY, dataPoint);
    }

    @Test
    public void getWeatherDataForRetrievesDataIfStationIsKnown() {
        DataPoint dataPoint = mock(DataPoint.class);
        weatherData.update("foo", DataPointType.WIND, dataPoint);
        Atmospheric atmosphericData = mock(Atmospheric.class);
        when(this.atmosphericInformation.toData()).thenReturn(atmosphericData);

        final Option<Atmospheric> result = weatherData.getWeatherDataFor("foo");

        Assertions.assertThat(result).contains(atmosphericData);
    }

    @Test
    public void removeStationIfStationDoesNotExist() {
        weatherData.removeStation("bar");

        Assertions.assertThat(weatherData.getWeatherData().toList()).isEmpty();
    }

    @Test
    public void removeStationExistingStationRepository() {
        DataPoint dataPoint = mock(DataPoint.class);
        weatherData.update("foo", DataPointType.WIND, dataPoint);

        weatherData.removeStation("foo");

        Assertions.assertThat(weatherData.getWeatherData().toList()).isEmpty();
    }
}