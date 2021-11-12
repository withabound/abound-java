package com.withabound.resources.base;

import java.util.Collections;
import java.util.Map;

class EmptyQueryParameters extends AboundQueryParameters {
  private static EmptyQueryParameters instance;

  public static EmptyQueryParameters getInstance() {
    if (instance == null) {
      instance = new EmptyQueryParameters();
    }

    return instance;
  }

  @Override
  public Map<String, Object> asMap() {
    return Collections.emptyMap();
  }
}
