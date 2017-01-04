package com.airport.weather.rest;

import com.airport.weather.Utils;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

    @Test
    public void canCreateCurrentTimestamp() {
        long currentTimeMillis = System.currentTimeMillis();
        Assertions.assertThat(Utils.getCurrentTimestamp()).isBetween(currentTimeMillis, currentTimeMillis + 10);
    }
    @Test
    public void calculatingDistanceHalfwayAroundTheEquatorShouldYieldHalveEarthCircumference() {
        Point point1 = new Point(0, 0);
        Point point2 = new Point(0, 180);

        assertThat(Utils.calculateDistance(point1, point2)).isEqualTo(20020.741, Offset.offset(1e-3));
        assertThat(Utils.calculateDistance(point2, point1)).isEqualTo(20020.741, Offset.offset(1e-3));
    }

    @Test
    public void calculatingDistanceFromTheEquatorToTheNorthernTropicShouldYieldRoughly2600Kilometers() {
        Point point1 = new Point(0, 11);
        Point point2 = new Point(23.5, 11);

        assertThat(Utils.calculateDistance(point1, point2)).isEqualTo(2613.819, Offset.offset(1e-3));
        assertThat(Utils.calculateDistance(point2, point1)).isEqualTo(2613.819, Offset.offset(1e-3));
    }

    static class Point implements com.airport.weather.data.Point {
        private final double latitude;
        private final double longitude;

        public Point(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public double latitude() {
            return latitude;
        }

        @Override
        public double longitude() {
            return longitude;
        }
    }


}