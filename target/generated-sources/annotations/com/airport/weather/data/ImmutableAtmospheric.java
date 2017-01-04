package com.airport.weather.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Immutable implementation of {@link Atmospheric}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableAtmospheric.builder()}.
 */
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@Generated({"Immutables.generator", "Atmospheric"})
@Immutable
public final class ImmutableAtmospheric implements Atmospheric {
  private final @Nullable DataPoint temperature;
  private final @Nullable DataPoint wind;
  private final @Nullable DataPoint humidity;
  private final @Nullable DataPoint precipitation;
  private final @Nullable DataPoint pressure;
  private final @Nullable DataPoint cloudCover;
  private final long lastUpdateTime;

  private ImmutableAtmospheric(
      @Nullable DataPoint temperature,
      @Nullable DataPoint wind,
      @Nullable DataPoint humidity,
      @Nullable DataPoint precipitation,
      @Nullable DataPoint pressure,
      @Nullable DataPoint cloudCover,
      long lastUpdateTime) {
    this.temperature = temperature;
    this.wind = wind;
    this.humidity = humidity;
    this.precipitation = precipitation;
    this.pressure = pressure;
    this.cloudCover = cloudCover;
    this.lastUpdateTime = lastUpdateTime;
  }

  /**
   * temperature in degrees celsius
   */
  @JsonProperty("temperature")
  @Override
  public @Nullable DataPoint temperature() {
    return temperature;
  }

  /**
   * wind speed in km/h
   */
  @JsonProperty("wind")
  @Override
  public @Nullable DataPoint wind() {
    return wind;
  }

  /**
   * humidity in percent
   */
  @JsonProperty("humidity")
  @Override
  public @Nullable DataPoint humidity() {
    return humidity;
  }

  /**
   * precipitation in cm
   */
  @JsonProperty("precipitation")
  @Override
  public @Nullable DataPoint precipitation() {
    return precipitation;
  }

  /**
   * pressure in mmHg
   */
  @JsonProperty("pressure")
  @Override
  public @Nullable DataPoint pressure() {
    return pressure;
  }

  /**
   * cloud cover percent from 0 - 100 (integer)
   */
  @JsonProperty("cloudCover")
  @Override
  public @Nullable DataPoint cloudCover() {
    return cloudCover;
  }

  /**
   * the last time this datatype was updated, in milliseconds since UTC epoch
   */
  @JsonProperty("lastUpdateTime")
  @Override
  public long lastUpdateTime() {
    return lastUpdateTime;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Atmospheric#temperature() temperature} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for temperature (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAtmospheric withTemperature(@Nullable DataPoint value) {
    if (this.temperature == value) return this;
    return new ImmutableAtmospheric(
        value,
        this.wind,
        this.humidity,
        this.precipitation,
        this.pressure,
        this.cloudCover,
        this.lastUpdateTime);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Atmospheric#wind() wind} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for wind (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAtmospheric withWind(@Nullable DataPoint value) {
    if (this.wind == value) return this;
    return new ImmutableAtmospheric(
        this.temperature,
        value,
        this.humidity,
        this.precipitation,
        this.pressure,
        this.cloudCover,
        this.lastUpdateTime);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Atmospheric#humidity() humidity} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for humidity (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAtmospheric withHumidity(@Nullable DataPoint value) {
    if (this.humidity == value) return this;
    return new ImmutableAtmospheric(
        this.temperature,
        this.wind,
        value,
        this.precipitation,
        this.pressure,
        this.cloudCover,
        this.lastUpdateTime);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Atmospheric#precipitation() precipitation} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for precipitation (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAtmospheric withPrecipitation(@Nullable DataPoint value) {
    if (this.precipitation == value) return this;
    return new ImmutableAtmospheric(
        this.temperature,
        this.wind,
        this.humidity,
        value,
        this.pressure,
        this.cloudCover,
        this.lastUpdateTime);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Atmospheric#pressure() pressure} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for pressure (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAtmospheric withPressure(@Nullable DataPoint value) {
    if (this.pressure == value) return this;
    return new ImmutableAtmospheric(
        this.temperature,
        this.wind,
        this.humidity,
        this.precipitation,
        value,
        this.cloudCover,
        this.lastUpdateTime);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Atmospheric#cloudCover() cloudCover} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for cloudCover (can be {@code null})
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAtmospheric withCloudCover(@Nullable DataPoint value) {
    if (this.cloudCover == value) return this;
    return new ImmutableAtmospheric(
        this.temperature,
        this.wind,
        this.humidity,
        this.precipitation,
        this.pressure,
        value,
        this.lastUpdateTime);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Atmospheric#lastUpdateTime() lastUpdateTime} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for lastUpdateTime
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAtmospheric withLastUpdateTime(long value) {
    if (this.lastUpdateTime == value) return this;
    return new ImmutableAtmospheric(
        this.temperature,
        this.wind,
        this.humidity,
        this.precipitation,
        this.pressure,
        this.cloudCover,
        value);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableAtmospheric} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableAtmospheric
        && equalTo((ImmutableAtmospheric) another);
  }

  private boolean equalTo(ImmutableAtmospheric another) {
    return Objects.equals(temperature, another.temperature)
        && Objects.equals(wind, another.wind)
        && Objects.equals(humidity, another.humidity)
        && Objects.equals(precipitation, another.precipitation)
        && Objects.equals(pressure, another.pressure)
        && Objects.equals(cloudCover, another.cloudCover)
        && lastUpdateTime == another.lastUpdateTime;
  }

  /**
   * Computes a hash code from attributes: {@code temperature}, {@code wind}, {@code humidity}, {@code precipitation}, {@code pressure}, {@code cloudCover}, {@code lastUpdateTime}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 31;
    h = h * 17 + Objects.hashCode(temperature);
    h = h * 17 + Objects.hashCode(wind);
    h = h * 17 + Objects.hashCode(humidity);
    h = h * 17 + Objects.hashCode(precipitation);
    h = h * 17 + Objects.hashCode(pressure);
    h = h * 17 + Objects.hashCode(cloudCover);
    h = h * 17 + Long.hashCode(lastUpdateTime);
    return h;
  }

  /**
   * Prints the immutable value {@code Atmospheric} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Atmospheric{"
        + "temperature=" + temperature
        + ", wind=" + wind
        + ", humidity=" + humidity
        + ", precipitation=" + precipitation
        + ", pressure=" + pressure
        + ", cloudCover=" + cloudCover
        + ", lastUpdateTime=" + lastUpdateTime
        + "}";
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements Atmospheric {
    @Nullable DataPoint temperature;
    @Nullable DataPoint wind;
    @Nullable DataPoint humidity;
    @Nullable DataPoint precipitation;
    @Nullable DataPoint pressure;
    @Nullable DataPoint cloudCover;
    long lastUpdateTime;
    boolean lastUpdateTimeIsSet;
    @JsonProperty("temperature")
    public void setTemperature(@Nullable DataPoint temperature) {
      this.temperature = temperature;
    }
    @JsonProperty("wind")
    public void setWind(@Nullable DataPoint wind) {
      this.wind = wind;
    }
    @JsonProperty("humidity")
    public void setHumidity(@Nullable DataPoint humidity) {
      this.humidity = humidity;
    }
    @JsonProperty("precipitation")
    public void setPrecipitation(@Nullable DataPoint precipitation) {
      this.precipitation = precipitation;
    }
    @JsonProperty("pressure")
    public void setPressure(@Nullable DataPoint pressure) {
      this.pressure = pressure;
    }
    @JsonProperty("cloudCover")
    public void setCloudCover(@Nullable DataPoint cloudCover) {
      this.cloudCover = cloudCover;
    }
    @JsonProperty("lastUpdateTime")
    public void setLastUpdateTime(long lastUpdateTime) {
      this.lastUpdateTime = lastUpdateTime;
      this.lastUpdateTimeIsSet = true;
    }
    @Override
    public DataPoint temperature() { throw new UnsupportedOperationException(); }
    @Override
    public DataPoint wind() { throw new UnsupportedOperationException(); }
    @Override
    public DataPoint humidity() { throw new UnsupportedOperationException(); }
    @Override
    public DataPoint precipitation() { throw new UnsupportedOperationException(); }
    @Override
    public DataPoint pressure() { throw new UnsupportedOperationException(); }
    @Override
    public DataPoint cloudCover() { throw new UnsupportedOperationException(); }
    @Override
    public long lastUpdateTime() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableAtmospheric fromJson(Json json) {
    ImmutableAtmospheric.Builder builder = ImmutableAtmospheric.builder();
    if (json.temperature != null) {
      builder.temperature(json.temperature);
    }
    if (json.wind != null) {
      builder.wind(json.wind);
    }
    if (json.humidity != null) {
      builder.humidity(json.humidity);
    }
    if (json.precipitation != null) {
      builder.precipitation(json.precipitation);
    }
    if (json.pressure != null) {
      builder.pressure(json.pressure);
    }
    if (json.cloudCover != null) {
      builder.cloudCover(json.cloudCover);
    }
    if (json.lastUpdateTimeIsSet) {
      builder.lastUpdateTime(json.lastUpdateTime);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link Atmospheric} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Atmospheric instance
   */
  public static ImmutableAtmospheric copyOf(Atmospheric instance) {
    if (instance instanceof ImmutableAtmospheric) {
      return (ImmutableAtmospheric) instance;
    }
    return ImmutableAtmospheric.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableAtmospheric ImmutableAtmospheric}.
   * @return A new ImmutableAtmospheric builder
   */
  public static ImmutableAtmospheric.Builder builder() {
    return new ImmutableAtmospheric.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableAtmospheric ImmutableAtmospheric}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_LAST_UPDATE_TIME = 0x1L;
    private long initBits = 0x1L;

    private @Nullable DataPoint temperature;
    private @Nullable DataPoint wind;
    private @Nullable DataPoint humidity;
    private @Nullable DataPoint precipitation;
    private @Nullable DataPoint pressure;
    private @Nullable DataPoint cloudCover;
    private long lastUpdateTime;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code Atmospheric} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(Atmospheric instance) {
      Objects.requireNonNull(instance, "instance");
      @Nullable DataPoint temperatureValue = instance.temperature();
      if (temperatureValue != null) {
        temperature(temperatureValue);
      }
      @Nullable DataPoint windValue = instance.wind();
      if (windValue != null) {
        wind(windValue);
      }
      @Nullable DataPoint humidityValue = instance.humidity();
      if (humidityValue != null) {
        humidity(humidityValue);
      }
      @Nullable DataPoint precipitationValue = instance.precipitation();
      if (precipitationValue != null) {
        precipitation(precipitationValue);
      }
      @Nullable DataPoint pressureValue = instance.pressure();
      if (pressureValue != null) {
        pressure(pressureValue);
      }
      @Nullable DataPoint cloudCoverValue = instance.cloudCover();
      if (cloudCoverValue != null) {
        cloudCover(cloudCoverValue);
      }
      lastUpdateTime(instance.lastUpdateTime());
      return this;
    }

    /**
     * Initializes the value for the {@link Atmospheric#temperature() temperature} attribute.
     * @param temperature The value for temperature (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("temperature")
    public final Builder temperature(@Nullable DataPoint temperature) {
      this.temperature = temperature;
      return this;
    }

    /**
     * Initializes the value for the {@link Atmospheric#wind() wind} attribute.
     * @param wind The value for wind (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("wind")
    public final Builder wind(@Nullable DataPoint wind) {
      this.wind = wind;
      return this;
    }

    /**
     * Initializes the value for the {@link Atmospheric#humidity() humidity} attribute.
     * @param humidity The value for humidity (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("humidity")
    public final Builder humidity(@Nullable DataPoint humidity) {
      this.humidity = humidity;
      return this;
    }

    /**
     * Initializes the value for the {@link Atmospheric#precipitation() precipitation} attribute.
     * @param precipitation The value for precipitation (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("precipitation")
    public final Builder precipitation(@Nullable DataPoint precipitation) {
      this.precipitation = precipitation;
      return this;
    }

    /**
     * Initializes the value for the {@link Atmospheric#pressure() pressure} attribute.
     * @param pressure The value for pressure (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("pressure")
    public final Builder pressure(@Nullable DataPoint pressure) {
      this.pressure = pressure;
      return this;
    }

    /**
     * Initializes the value for the {@link Atmospheric#cloudCover() cloudCover} attribute.
     * @param cloudCover The value for cloudCover (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("cloudCover")
    public final Builder cloudCover(@Nullable DataPoint cloudCover) {
      this.cloudCover = cloudCover;
      return this;
    }

    /**
     * Initializes the value for the {@link Atmospheric#lastUpdateTime() lastUpdateTime} attribute.
     * @param lastUpdateTime The value for lastUpdateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("lastUpdateTime")
    public final Builder lastUpdateTime(long lastUpdateTime) {
      this.lastUpdateTime = lastUpdateTime;
      initBits &= ~INIT_BIT_LAST_UPDATE_TIME;
      return this;
    }

    /**
     * Builds a new {@link ImmutableAtmospheric ImmutableAtmospheric}.
     * @return An immutable instance of Atmospheric
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableAtmospheric build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableAtmospheric(temperature, wind, humidity, precipitation, pressure, cloudCover, lastUpdateTime);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<String>();
      if ((initBits & INIT_BIT_LAST_UPDATE_TIME) != 0) attributes.add("lastUpdateTime");
      return "Cannot build Atmospheric, some of required attributes are not set " + attributes;
    }
  }
}
