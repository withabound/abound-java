package com.withabound.resources.asserts;

import com.withabound.resources.base.AboundBulkResponse;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

public class AboundBulkResponseAssert<T>
    extends AbstractAssert<AboundBulkResponseAssert<T>, AboundBulkResponse<T>> {
  public static <T> AboundBulkResponseAssert<T> assertThat(final AboundBulkResponse<T> actual) {
    return new AboundBulkResponseAssert<>(actual);
  }

  public AboundBulkResponseAssert<T> hasResponseMetadata() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getRequest()).isNotNull();
    Assertions.assertThat(actual.getRequest().getTimestamp())
        .withFailMessage(AboundResponseAssert.CREATED_TIMESTAMP_ASSERTION_ERROR_MESSAGE)
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
    Assertions.assertThat(actual.getRequest().getRequestId()).isNotEmpty();

    return this;
  }

  private AboundBulkResponseAssert(AboundBulkResponse<T> actual) {
    super(actual, AboundBulkResponseAssert.class);
  }
}
