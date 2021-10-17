package com.withabound.resources.base;

import java.util.List;
import lombok.Getter;

/**
 * Represents the object returned by Abound's APIs that return many elements of the underlying
 * domain data.
 *
 * <p>See {@link AboundResponse} for the corresponding object that holds a single element.
 *
 * @param <T> type parameter for the underlying data elements
 */
@Getter
public class AboundBulkResponse<T> {
  private List<T> data;

  private int count;

  private String nextPage;

  private RequestMetadata request;
}
