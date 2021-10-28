package com.withabound.resources.asserts;

import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

public class AboundResponseAssert<T>
    extends AbstractAssert<AboundResponseAssert<T>, AboundResponse<T>> {
  public static <T> AboundResponseAssert<T> assertThat(final AboundResponse<T> actual) {
    return new AboundResponseAssert<>(actual);
  }

  public AboundResponseAssert<T> hasResponseMetadata() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getRequest()).isNotNull();
    Assertions.assertThat(actual.getRequest().getTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(1000L));
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
