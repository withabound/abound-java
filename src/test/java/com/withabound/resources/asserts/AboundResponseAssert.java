package com.withabound.resources.asserts;

import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

public class AboundResponseAssert<T>
    extends AbstractAssert<AboundResponseAssert<T>, AboundResponse<T>> {
  static final String CREATED_TIMESTAMP_ASSERTION_ERROR_MESSAGE =
      "This test asserts that an object's createdTimestamp, returned from Abound's APIs, is within 1,000 milliseconds "
          + "of the system clock on which this test runs, when it receives the response payload. As a result, test "
          + "failures/flakiness here generally might hint at temporary clock drift or network congestion, and hopefully "
          + "nothing more serious. Continued, consistent failures may indicate a pervasive issue. If flakiness increases, "
          + "the offset of 1,000 milliseconds could also be increased.";

  public static <T> AboundResponseAssert<T> assertThat(final AboundResponse<T> actual) {
    return new AboundResponseAssert<>(actual);
  }

  public AboundResponseAssert<T> hasResponseMetadata() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getRequest()).isNotNull();
    Assertions.assertThat(actual.getRequest().getTimestamp())
        .withFailMessage(CREATED_TIMESTAMP_ASSERTION_ERROR_MESSAGE)
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
    Assertions.assertThat(actual.getRequest().getRequestId()).isNotEmpty();

    return this;
  }

  public AboundResponseAssert<T> hasEmptyDataObject() {
    Assertions.assertThat(actual.getData()).isInstanceOf(EmptyJsonObject.class);

    return this;
  }

  private AboundResponseAssert(AboundResponse<T> actual) {
    super(actual, AboundResponseAssert.class);
  }
}
