package com.airport.weather.data;

import com.google.gson.*;
import com.google.gson.reflect.*;
import com.google.gson.stream.*;
import java.io.IOException;
import javax.annotation.Generated;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@code TypeAdapterFactory} that handles all of the immutable types generated under {@code DataPoint}.
 * @see ImmutableDataPoint
 */
@SuppressWarnings("all")
@Generated({"Gsons.generator", "com.airport.weather.data.DataPoint"})
@ParametersAreNonnullByDefault
public final class GsonAdaptersDataPoint implements TypeAdapterFactory {
  @SuppressWarnings({"unchecked", "raw"}) // safe unchecked, types are verified in runtime
  @Override
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    if (DataPointTypeAdapter.adapts(type)) {
      return (TypeAdapter<T>) new DataPointTypeAdapter(gson);
    }
    return null;
  }

  @Override
  public String toString() {
    return "GsonAdaptersDataPoint(DataPoint)";
  }

  @SuppressWarnings({"unchecked", "raw"}) // safe unchecked, types are verified in runtime
  private static class DataPointTypeAdapter extends TypeAdapter<DataPoint> {

    DataPointTypeAdapter(Gson gson) {
    } 

    static boolean adapts(TypeToken<?> type) {
      return DataPoint.class == type.getRawType()
          || ImmutableDataPoint.class == type.getRawType();
    }

    @Override
    public void write(JsonWriter out, DataPoint value) throws IOException {
      if (value == null) {
        out.nullValue();
      } else {
        writeDataPoint(out, value);
      }
    }

    @Override
    public DataPoint read(JsonReader in) throws IOException {
      if (in.peek() == JsonToken.NULL) {
        in.nextNull();
        return null;
      }
      return readDataPoint(in);
    }

    private void writeDataPoint(JsonWriter out, DataPoint instance)
        throws IOException {
      out.beginObject();
      out.name("mean");
      out.value(instance.mean());
      out.name("first");
      out.value(instance.first());
      out.name("median");
      out.value(instance.median());
      out.name("last");
      out.value(instance.last());
      out.name("count");
      out.value(instance.count());
      out.endObject();
    }

    private DataPoint readDataPoint(JsonReader in)
        throws IOException {
      ImmutableDataPoint.Builder builder = ImmutableDataPoint.builder();
      in.beginObject();
      while (in.hasNext()) {
        eachAttribute(in, builder);
      }
      in.endObject();
      return builder.build();
    }

    private void eachAttribute(JsonReader in, ImmutableDataPoint.Builder builder)
        throws IOException {
      String attributeName = in.nextName();
      switch (attributeName.charAt(0)) {
      case 'm':
        if ("mean".equals(attributeName)) {
          readInMean(in, builder);
          return;
        }
        if ("median".equals(attributeName)) {
          readInMedian(in, builder);
          return;
        }
        break;
      case 'f':
        if ("first".equals(attributeName)) {
          readInFirst(in, builder);
          return;
        }
        break;
      case 'l':
        if ("last".equals(attributeName)) {
          readInLast(in, builder);
          return;
        }
        break;
      case 'c':
        if ("count".equals(attributeName)) {
          readInCount(in, builder);
          return;
        }
        break;
      default:
      }
      in.skipValue();
    }

    private void readInMean(JsonReader in, ImmutableDataPoint.Builder builder)
        throws IOException {
      builder.mean(in.nextDouble());
    }

    private void readInFirst(JsonReader in, ImmutableDataPoint.Builder builder)
        throws IOException {
      builder.first(in.nextInt());
    }

    private void readInMedian(JsonReader in, ImmutableDataPoint.Builder builder)
        throws IOException {
      builder.median(in.nextInt());
    }

    private void readInLast(JsonReader in, ImmutableDataPoint.Builder builder)
        throws IOException {
      builder.last(in.nextInt());
    }

    private void readInCount(JsonReader in, ImmutableDataPoint.Builder builder)
        throws IOException {
      builder.count(in.nextInt());
    }
  }
}
