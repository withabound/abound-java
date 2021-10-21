package com.withabound.resources.base;

import lombok.Getter;

/** Every API response returns metadata about the request that it served. */
@Getter
public class RequestMetadata {
  private Long timestamp;

  private String requestId;
}
