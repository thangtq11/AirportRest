package com.airport.weather.data;

import com.airport.weather.Utils;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.control.Option;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
/**
 * Atmospheric information encapsulates sensor information for a particular location
 */
public class AtmosphericInformation {
    /** for validating input atmospheric information of a data point*/
    private static final Map<DataPointType, Predicate<DataPoint>> validateDataPointByType = HashMap.ofEntries(
            Tuple.of(DataPointType.WIND, dataPoint -> dataPoint.mean() >= 0.0),
            Tuple.of(DataPointType.TEMPERATURE, dataPoint -> dataPoint.mean() >= -50.0 && dataPoint.mean() < 100.0),
            Tuple.of(DataPointType.HUMIDITY, dataPoint -> dataPoint.mean() >= 0.0 && dataPoint.mean() < 100.0),
            Tuple.of(DataPointType.PRESSURE, dataPoint -> dataPoint.mean() >= 650.0 && dataPoint.mean() < 800.0),
            Tuple.of(DataPointType.CLOUDCOVER, dataPoint -> dataPoint.mean() >= 0 && dataPoint.mean() < 100.0),
            Tuple.of(DataPointType.PRECIPITATION, dataPoint -> dataPoint.mean() >= 0 && dataPoint.mean() < 100.0)
    );
    /** automatic building Atmospheric object by type*/
    private static final List<Tuple2<DataPointType, BiConsumer<ImmutableAtmospheric.Builder, DataPoint>>> pointDataTypesBuilderMethods = List.of(
            Tuple.of(DataPointType.WIND, (BiConsumer<ImmutableAtmospheric.Builder, DataPoint>) ImmutableAtmospheric.Builder::wind),
            Tuple.of(DataPointType.TEMPERATURE, (BiConsumer<ImmutableAtmospheric.Builder, DataPoint>) ImmutableAtmospheric.Builder::temperature),
            Tuple.of(DataPointType.HUMIDITY, (BiConsumer<ImmutableAtmospheric.Builder, DataPoint>) ImmutableAtmospheric.Builder::humidity),
            Tuple.of(DataPointType.PRESSURE, (BiConsumer<ImmutableAtmospheric.Builder, DataPoint>) ImmutableAtmospheric.Builder::pressure),
            Tuple.of(DataPointType.CLOUDCOVER, (BiConsumer<ImmutableAtmospheric.Builder, DataPoint>) ImmutableAtmospheric.Builder::cloudCover),
            Tuple.of(DataPointType.PRECIPITATION, (BiConsumer<ImmutableAtmospheric.Builder, DataPoint>) ImmutableAtmospheric.Builder::precipitation)
    );
    /** mapping type of atmospheric information with a data point*/
    private final ConcurrentHashMap<DataPointType, DataPoint> dataPointByType = new ConcurrentHashMap<>();

    /** getting from current update time*/
    private long lastUpdateTime = 0L;

    /**
     * updating a type of atmospheric information with data
     * @param dataType one of the six data point type
     * @param data real data of a collected point.
     */
    public void update(DataPointType dataType, DataPoint data) {
        final Boolean shouldAddData =
                validateDataPointByType
                        .get(dataType)
                        .map(rulePredicate -> rulePredicate.test(data))
                        .getOrElse(true);

        if (shouldAddData) {
            dataPointByType.put(dataType, data);
            lastUpdateTime = Utils.getCurrentTimestamp();
        }
    }

    /**
     * getting atmospheric information of a collected point.
     * @return atmospheric object
     */
    public Atmospheric toData() {
        final ImmutableAtmospheric.Builder builder = ImmutableAtmospheric.builder();

        pointDataTypesBuilderMethods.forEach(dataTypeAndBuilderMethod -> {
            DataPointType dataType = dataTypeAndBuilderMethod._1();
            BiConsumer<ImmutableAtmospheric.Builder, DataPoint> builderAdapter = dataTypeAndBuilderMethod._2();

            Option.of(dataPointByType
                    .get(dataType))
                    .forEach(data -> {
                        builderAdapter.accept(builder, data);
                    });
        });

        builder.lastUpdateTime(lastUpdateTime);

        return builder.build();
    }
}
