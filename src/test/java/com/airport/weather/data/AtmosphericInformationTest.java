package com.airport.weather.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AtmosphericInformationTest {


    @InjectMocks
    private AtmosphericInformation repository;

    @Test
    public void canAcceptWindDataInValidRange() {
        repository.update(DataPointType.WIND, createData(0.0));

        assertThat(repository.toData().wind()).isEqualTo(createData(0.0));
    }

    @Test
    public void canIgnoreWindDataInValidRange() {
        repository.update(DataPointType.WIND, createData(-0.0001));

        assertThat(repository.toData().wind()).isNull();
    }

    @Test
    public void canAcceptTemperatureDataAtLowestValidValue() {
        final DataPoint data = createData(-50.0);
        repository.update(DataPointType.TEMPERATURE, data);

        assertThat(repository.toData().temperature()).isEqualTo(data);
    }

    @Test
    public void canAcceptTemperatureDataAtHighestValidValue() {
        final DataPoint data = createData(99.999999);
        repository.update(DataPointType.TEMPERATURE, data);

        assertThat(repository.toData().temperature()).isEqualTo(data);
    }

    @Test
    public void canIgnoreTemperatureDataBelowLowestValidValue() {
        repository.update(DataPointType.TEMPERATURE, createData(-50.0001));

        assertThat(repository.toData().temperature()).isNull();
    }

    @Test
    public void canIgnoreTemperatureDataAboveHighestValidValue() {
        repository.update(DataPointType.TEMPERATURE, createData(100.0));

        assertThat(repository.toData().temperature()).isNull();
    }

    private ImmutableDataPoint createData(double mean) {
        return ImmutableDataPoint.builder().first(0).median(1).last(0).count(1).mean(mean).build();
    }
}