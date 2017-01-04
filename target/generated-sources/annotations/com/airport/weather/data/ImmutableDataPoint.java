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
 * Immutable implementation of {@link DataPoint}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableDataPoint.builder()}.
 */
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@Generated({"Immutables.generator", "DataPoint"})
@Immutable
public final class ImmutableDataPoint implements DataPoint {
  private final double mean;
  private final int first;
  private final int median;
  private final int last;
  private final int count;

  private ImmutableDataPoint(double mean, int first, int median, int last, int count) {
    this.mean = mean;
    this.first = first;
    this.median = median;
    this.last = last;
    this.count = count;
  }

  /**
   * the mean of the observations
   */
  @JsonProperty("mean")
  @Override
  public double mean() {
    return mean;
  }

  /**
   * 1st quartile -- useful as a lower bound
   */
  @JsonProperty("first")
  @Override
  public int first() {
    return first;
  }

  /**
   * 2nd quartile -- median value
   */
  @JsonProperty("median")
  @Override
  public int median() {
    return median;
  }

  /**
   * 3rd quartile value -- less noisy upper value
   */
  @JsonProperty("last")
  @Override
  public int last() {
    return last;
  }

  /**
   * the total number of measurements
   */
  @JsonProperty("count")
  @Override
  public int count() {
    return count;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DataPoint#mean() mean} attribute.
   * A value strict bits equality used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for mean
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDataPoint withMean(double value) {
    if (Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(value)) return this;
    return new ImmutableDataPoint(value, this.first, this.median, this.last, this.count);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DataPoint#first() first} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for first
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDataPoint withFirst(int value) {
    if (this.first == value) return this;
    return new ImmutableDataPoint(this.mean, value, this.median, this.last, this.count);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DataPoint#median() median} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for median
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDataPoint withMedian(int value) {
    if (this.median == value) return this;
    return new ImmutableDataPoint(this.mean, this.first, value, this.last, this.count);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DataPoint#last() last} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for last
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDataPoint withLast(int value) {
    if (this.last == value) return this;
    return new ImmutableDataPoint(this.mean, this.first, this.median, value, this.count);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link DataPoint#count() count} attribute.
   * A value equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for count
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableDataPoint withCount(int value) {
    if (this.count == value) return this;
    return new ImmutableDataPoint(this.mean, this.first, this.median, this.last, value);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableDataPoint} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableDataPoint
        && equalTo((ImmutableDataPoint) another);
  }

  private boolean equalTo(ImmutableDataPoint another) {
    return Double.doubleToLongBits(mean) == Double.doubleToLongBits(another.mean)
        && first == another.first
        && median == another.median
        && last == another.last
        && count == another.count;
  }

  /**
   * Computes a hash code from attributes: {@code mean}, {@code first}, {@code median}, {@code last}, {@code count}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 31;
    h = h * 17 + Double.hashCode(mean);
    h = h * 17 + first;
    h = h * 17 + median;
    h = h * 17 + last;
    h = h * 17 + count;
    return h;
  }

  /**
   * Prints the immutable value {@code DataPoint} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "DataPoint{"
        + "mean=" + mean
        + ", first=" + first
        + ", median=" + median
        + ", last=" + last
        + ", count=" + count
        + "}";
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements DataPoint {
    double mean;
    boolean meanIsSet;
    int first;
    boolean firstIsSet;
    int median;
    boolean medianIsSet;
    int last;
    boolean lastIsSet;
    int count;
    boolean countIsSet;
    @JsonProperty("mean")
    public void setMean(double mean) {
      this.mean = mean;
      this.meanIsSet = true;
    }
    @JsonProperty("first")
    public void setFirst(int first) {
      this.first = first;
      this.firstIsSet = true;
    }
    @JsonProperty("median")
    public void setMedian(int median) {
      this.median = median;
      this.medianIsSet = true;
    }
    @JsonProperty("last")
    public void setLast(int last) {
      this.last = last;
      this.lastIsSet = true;
    }
    @JsonProperty("count")
    public void setCount(int count) {
      this.count = count;
      this.countIsSet = true;
    }
    @Override
    public double mean() { throw new UnsupportedOperationException(); }
    @Override
    public int first() { throw new UnsupportedOperationException(); }
    @Override
    public int median() { throw new UnsupportedOperationException(); }
    @Override
    public int last() { throw new UnsupportedOperationException(); }
    @Override
    public int count() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableDataPoint fromJson(Json json) {
    ImmutableDataPoint.Builder builder = ImmutableDataPoint.builder();
    if (json.meanIsSet) {
      builder.mean(json.mean);
    }
    if (json.firstIsSet) {
      builder.first(json.first);
    }
    if (json.medianIsSet) {
      builder.median(json.median);
    }
    if (json.lastIsSet) {
      builder.last(json.last);
    }
    if (json.countIsSet) {
      builder.count(json.count);
    }
    return builder.build();
  }

  /**
   * Creates an immutable copy of a {@link DataPoint} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable DataPoint instance
   */
  public static ImmutableDataPoint copyOf(DataPoint instance) {
    if (instance instanceof ImmutableDataPoint) {
      return (ImmutableDataPoint) instance;
    }
    return ImmutableDataPoint.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableDataPoint ImmutableDataPoint}.
   * @return A new ImmutableDataPoint builder
   */
  public static ImmutableDataPoint.Builder builder() {
    return new ImmutableDataPoint.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableDataPoint ImmutableDataPoint}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_MEAN = 0x1L;
    private static final long INIT_BIT_FIRST = 0x2L;
    private static final long INIT_BIT_MEDIAN = 0x4L;
    private static final long INIT_BIT_LAST = 0x8L;
    private static final long INIT_BIT_COUNT = 0x10L;
    private long initBits = 0x1fL;

    private double mean;
    private int first;
    private int median;
    private int last;
    private int count;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code DataPoint} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Builder from(DataPoint instance) {
      Objects.requireNonNull(instance, "instance");
      mean(instance.mean());
      first(instance.first());
      median(instance.median());
      last(instance.last());
      count(instance.count());
      return this;
    }

    /**
     * Initializes the value for the {@link DataPoint#mean() mean} attribute.
     * @param mean The value for mean 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("mean")
    public final Builder mean(double mean) {
      this.mean = mean;
      initBits &= ~INIT_BIT_MEAN;
      return this;
    }

    /**
     * Initializes the value for the {@link DataPoint#first() first} attribute.
     * @param first The value for first 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("first")
    public final Builder first(int first) {
      this.first = first;
      initBits &= ~INIT_BIT_FIRST;
      return this;
    }

    /**
     * Initializes the value for the {@link DataPoint#median() median} attribute.
     * @param median The value for median 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("median")
    public final Builder median(int median) {
      this.median = median;
      initBits &= ~INIT_BIT_MEDIAN;
      return this;
    }

    /**
     * Initializes the value for the {@link DataPoint#last() last} attribute.
     * @param last The value for last 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("last")
    public final Builder last(int last) {
      this.last = last;
      initBits &= ~INIT_BIT_LAST;
      return this;
    }

    /**
     * Initializes the value for the {@link DataPoint#count() count} attribute.
     * @param count The value for count 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("count")
    public final Builder count(int count) {
      this.count = count;
      initBits &= ~INIT_BIT_COUNT;
      return this;
    }

    /**
     * Builds a new {@link ImmutableDataPoint ImmutableDataPoint}.
     * @return An immutable instance of DataPoint
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableDataPoint build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableDataPoint(mean, first, median, last, count);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<String>();
      if ((initBits & INIT_BIT_MEAN) != 0) attributes.add("mean");
      if ((initBits & INIT_BIT_FIRST) != 0) attributes.add("first");
      if ((initBits & INIT_BIT_MEDIAN) != 0) attributes.add("median");
      if ((initBits & INIT_BIT_LAST) != 0) attributes.add("last");
      if ((initBits & INIT_BIT_COUNT) != 0) attributes.add("count");
      return "Cannot build DataPoint, some of required attributes are not set " + attributes;
    }
  }
}
