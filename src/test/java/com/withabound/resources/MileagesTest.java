package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.exceptions.AboundApiException;
import com.withabound.models.mileages.Mileage;
import com.withabound.models.mileages.MileageRequest;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.MileageAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MileagesTest extends AbstractAboundTest {
  public static final String TEST_MILEAGE_ID = "mileageId_test4af7070cfb04a12552a1950e2f0afa660fba";

  @Test
  public void testCreate() throws IOException {
    final String randomDate1 = TestUtils.randomDate();
    final String randomDescription1 = TestUtils.randomAlphabetic();

    final String randomDate2 = TestUtils.randomDate();
    final String randomDescription2 = TestUtils.randomAlphabetic();

    final List<MileageRequest> toCreate =
        Arrays.asList(
            MileageRequest.builder()
                .date(randomDate1)
                .distance(21.1)
                .description(randomDescription1)
                .build(),
            MileageRequest.builder()
                .date(randomDate2)
                .distance(81.9)
                .description(randomDescription2)
                .build());

    final AboundBulkResponse<Mileage> created =
        getAboundClient().mileages().create(TestUtils.TEST_USER_ID, toCreate);

    AboundBulkResponseAssert.assertThat(created).hasResponseMetadata();

    final List<Mileage> createdMileages = created.getData();
    assertThat(createdMileages).hasSize(2);
    assertThat(createdMileages.get(0).getDate()).isEqualTo(randomDate1);
    assertThat(createdMileages.get(0).getDescription().orElse(null)).isEqualTo(randomDescription1);
    assertThat(createdMileages.get(0).getDistance()).isEqualTo(21.1);

    assertThat(createdMileages.get(1).getDate()).isEqualTo(randomDate2);
    assertThat(createdMileages.get(1).getDescription().orElse(null)).isEqualTo(randomDescription2);
    assertThat(createdMileages.get(1).getDistance()).isEqualTo(81.9);
  }

  @Test
  public void testCreateWithEmptyListThrowsAboundApiException() {
    final List<MileageRequest> toCreate = Collections.emptyList();

    Assertions.assertThatThrownBy(
            () -> getAboundClient().mileages().create(TestUtils.TEST_USER_ID, toCreate))
        .isInstanceOf(AboundApiException.class)
        .hasMessage("Missing mileages objects in request body (code b7c845bd)")
        .hasFieldOrPropertyWithValue("statusCode", 400)
        .hasFieldOrProperty("request");
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<Mileage> response =
        getAboundClient().mileages().retrieve(TestUtils.TEST_USER_ID, TEST_MILEAGE_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Mileage retrieved = response.getData();

    MileageAssert.assertThat(retrieved).is09Jan2020OnSiteClientVisit();
  }
}
