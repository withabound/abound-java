package com.withabound.resources.base;

import lombok.Getter;

/**
 * Represents the object returned by Abound's APIs that return a single element of underlying domain
 * data.
 *
 * <p>See {@link AboundBulkResponse} for the corresponding object that holds many elements.
 *
 * @param <T> type parameter for the underlying data element
 */
@Getter
public class AboundResponse<T> {
  private T data;

  private RequestMetadata request;
}
