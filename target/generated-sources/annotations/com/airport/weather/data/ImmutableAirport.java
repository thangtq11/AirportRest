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
 * Immutable implementation of {@link Airport}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableAirport.builder()}.
 */
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@Generated({"Immutables.generator", "Airport"})
@Immutable
public final class ImmutableAirport implements Airport {
  private final String iataCode;
  private final double latitude;
  private final double longitude;

  private ImmutableAirport(String iataCode, double latitude, double longitude) {
    this.iataCode = iataCode;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * @return The value of the {@code iataCode} attribute
   */
  @JsonProperty("iata")
  @Override
  public String iataCode() {
    return iataCode;
  }

  /**
   * Decimal degrees, usually to six significant digits.
   * Negative is South, positive is North
   */
  @JsonProperty("latitude")
  @Override
  public double latitude() {
    return latitude;
  }

  /**
   * Decimal degrees, usually to six significant digits.
   * Negative is West, positive is East
   */
  @JsonProperty("longitude")
  @Override
  public double longitude() {
    return longitude;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Airport#iataCode() iataCode} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for iataCode
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAirport withIataCode(String value) {
    if (this.iataCode.equals(value)) return this;
    String newValue = Objects.requireNonNull(value, "iataCode");
    return new ImmutableAirport(newValue, this.latitude, this.longitude);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Airport#latitude() latitude} attribute.
   * A value strict bits equality used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for latitude
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAirport withLatitude(double value) {
    if (Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(value)) return this;
    return new ImmutableAirport(this.iataCode, value, this.longitude);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link Airport#longitude() longitude} attribute.
   * A value strict bits equality used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for longitude
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableAirport withLongitude(double value) {
    if (Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(value)) return this;
    return new ImmutableAirport(this.iataCode, this.latitude, value);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableAirport} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableAirport
        && equalTo((ImmutableAirport) another);
  }

  private boolean equalTo(ImmutableAirport another) {
    return iataCode.equals(another.iataCode)
        && Double.doubleToLongBits(latitude) == Double.doubleToLongBits(another.latitude)
        && Double.doubleToLongBits(longitude) == Double.doubleToLongBits(another.longitude);
  }

  /**
   * Computes a hash code from attributes: {@code iataCode}, {@code latitude}, {@code longitude}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 31;
    h = h * 17 + iataCode.hashCode();
    h = h * 17 + Double.hashCode(latitude);
    h = h * 17 + Double.hashCode(longitude);
    return h;
  }

  /**
   * Prints the immutable value {@code Airport} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Airport{"
        + "iataCode=" + iataCode
        + ", latitude=" + latitude
        + ", longitude=" + longitude
        + "}";
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements Airport {
    @Nullable String iataCode;
    double latitude;
    boolean latitudeIsSet;
    double longitude;
    boolean longitudeIsSet;
    @JsonProperty("iata")
    public void setIataCode(String iataCode) {
      this.iataCode = iataCode;
    }
    @JsonProperty("latitude")
    public void setLatitude(double latitude) {
      this.latitude = latitude;
      this.latitudeIsSet = true;
    }
    @JsonProperty("longitude")
    public void setLongitude(double longitude) {
      this.longitude = longitude;
      this.longitudeIsSet = true;
    }
    @Override
    public String iataCode() { throw new UnsupportedOperationException(); }
    @Override
    public double latitude() { throw new UnsupportedOperationException(); }
    @Override
    public double longitude() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableAirport fromJson(Json json) {
    ImmutableAirport.Builder builder = ImmutableAirport.builder();
    if (json.iataCode != null) {
      builder.iataCode(json.iataCode);
    }
    if (json.latitudeIsSet) {
      builder.latitude(json.latitude);
    }
    if (json.longitudeIsSet) {
      builder.longitude(json.longitude);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link Airport} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable Airport instance
   */
  public static ImmutableAirport copyOf(Airport instance) {
    if (instance instanceof ImmutableAirport) {
      return (ImmutableAirport) instance;
    }
    return ImmutableAirport.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableAirport ImmutableAirport}.
   * @return A new ImmutableAirport builder
   */
  public static ImmutableAirport.Builder builder() {
    return new ImmutableAirport.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableAirport ImmutableAirport}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_IATA_CODE = 0x1L;
    private static final long INIT_BIT_LATITUDE = 0x2L;
    private static final long INIT_BIT_LONGITUDE = 0x4L;
    private long initBits = 0x7L;

    private @Nullable String iataCode;
    private double latitude;
    private double longitude;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.airport.weather.data.Point} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(Point instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.airport.weather.data.Airport} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(Airport instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return this;
    }

    private void from(Object object) {
      if (object instanceof Point) {
        Point instance = (Point) object;
        latitude(instance.latitude());
        longitude(instance.longitude());
      }
      if (object instanceof Airport) {
        Airport instance = (Airport) object;
        iataCode(instance.iataCode());
      }
    }

    /**
     * Initializes the value for the {@link Airport#iataCode() iataCode} attribute.
     * @param iataCode The value for iataCode 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("iata")
    public final Builder iataCode(String iataCode) {
      this.iataCode = Objects.requireNonNull(iataCode, "iataCode");
      initBits &= ~INIT_BIT_IATA_CODE;
      return this;
    }

    /**
     * Initializes the value for the {@link Airport#latitude() latitude} attribute.
     * @param latitude The value for latitude 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("latitude")
    public final Builder latitude(double latitude) {
      this.latitude = latitude;
      initBits &= ~INIT_BIT_LATITUDE;
      return this;
    }

    /**
     * Initializes the value for the {@link Airport#longitude() longitude} attribute.
     * @param longitude The value for longitude 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("longitude")
    public final Builder longitude(double longitude) {
      this.longitude = longitude;
      initBits &= ~INIT_BIT_LONGITUDE;
      return this;
    }

    /**
     * Builds a new {@link ImmutableAirport ImmutableAirport}.
     * @return An immutable instance of Airport
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableAirport build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableAirport(iataCode, latitude, longitude);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<String>();
      if ((initBits & INIT_BIT_IATA_CODE) != 0) attributes.add("iataCode");
      if ((initBits & INIT_BIT_LATITUDE) != 0) attributes.add("latitude");
      if ((initBits & INIT_BIT_LONGITUDE) != 0) attributes.add("longitude");
      return "Cannot build Airport, some of required attributes are not set " + attributes;
    }
  }
}
