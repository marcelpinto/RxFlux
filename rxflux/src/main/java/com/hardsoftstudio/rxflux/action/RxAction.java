package com.hardsoftstudio.rxflux.action;

import android.support.v4.util.ArrayMap;

/**
 * Created by marcel on 13/08/15.
 */
public class RxAction {
  private final String type;
  private final ArrayMap<String, Object> data;

  RxAction(String type, ArrayMap<String, Object> data) {
    this.type = type;
    this.data = data;
  }

  public static Builder type(String type) {
    return new Builder().with(type);
  }

  public String getType() {
    return type;
  }

  public ArrayMap<String, Object> getData() {
    return data;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof RxAction)) return false;

    RxAction rxAction = (RxAction) o;

    if (!type.equals(rxAction.type)) return false;
    return !(data != null ? !data.equals(rxAction.data) : rxAction.data != null);
  }

  @Override
  public int hashCode() {
    int result = type.hashCode();
    result = 31 * result + (data != null ? data.hashCode() : 0);
    return result;
  }

  public static class Builder {

    private String type;
    private ArrayMap<String, Object> data;

    Builder with(String type) {
      if (type == null) {
        throw new IllegalArgumentException("Type may not be null.");
      }
      this.type = type;
      this.data = new ArrayMap<>();
      return this;
    }

    public Builder bundle(String key, Object value) {
      if (key == null) {
        throw new IllegalArgumentException("Key may not be null.");
      }

      if (value == null) {
        throw new IllegalArgumentException("Value may not be null.");
      }
      data.put(key, value);
      return this;
    }

    public RxAction build() {
      if (type == null || type.isEmpty()) {
        throw new IllegalArgumentException("At least one key is required.");
      }
      return new RxAction(type, data);
    }
  }
}