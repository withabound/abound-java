package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.models.payers.Payer;
import com.withabound.models.payers.PayerParams;
import com.withabound.models.payers.PayerRequest;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.PayerAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

public class PayersTest extends AbstractAboundTest {
  public static final String TEST_PAYER_ID = "payerId_test3629d683f7534f096ccd8d236e24887c9891";

  @Test
  public void testCreate() throws IOException {
    final String name = TestUtils.randomAlphabetic();
    final String address = String.format("588 %s st.", TestUtils.randomAlphabetic());
    final String address2 = String.format("Suite %s", TestUtils.randomNumberString(3));
    final String city = TestUtils.randomAlphabetic();
    final String zipcode = TestUtils.randomNumberString(5);
    final String phoneNumber = TestUtils.randomNumberString(10);
    final String tin = TestUtils.randomNumberString(9);

    final PayerRequest toCreate =
        PayerRequest.builder()
            .name(name)
            .address(address)
            .address2(address2)
            .city(city)
            .state("NY")
            .zipcode(zipcode)
            .country("US")
            .phoneNumber(phoneNumber)
            .taxIdNumber(tin)
            .build();

    final AboundBulkResponse<Payer> response =
        getAboundClient().payers().create(Collections.singletonList(toCreate));
    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Payer> createdPayers = response.getData();
    assertThat(createdPayers).isNotNull().hasSize(1);

    final Payer created = createdPayers.get(0);
    assertThat(created).isNotNull();
    assertThat(created.getPayerId()).isEqualTo(TEST_PAYER_ID);
    assertThat(created.getName()).isEqualTo(name);
    assertThat(created.getAddress()).isEqualTo(address);
    assertThat(created.getAddress2()).isEqualTo(Optional.of(address2));
    assertThat(created.getCity()).isEqualTo(city);
    assertThat(created.getState()).isEqualTo("NY");
    assertThat(created.getZipcode()).isEqualTo(zipcode);
    assertThat(created.getCountry()).isEqualTo("US");
    assertThat(created.getPhoneNumber()).isEqualTo(phoneNumber);
    assertThat(created.getTaxIdNumber()).isNull();
    assertThat(created.getForeignId()).isEqualTo(Optional.of("your_foreign_id"));
  }

  @Test
  public void testCreateV2() throws IOException {
    final String name = TestUtils.randomAlphabetic();
    final String address = String.format("588 %s st.", TestUtils.randomAlphabetic());
    final String address2 = String.format("Suite %s", TestUtils.randomNumberString(3));
    final String city = TestUtils.randomAlphabetic();
    final String zipcode = TestUtils.randomNumberString(5);
    final String phoneNumber = TestUtils.randomNumberString(10);
    final String tin = TestUtils.randomNumberString(9);

    final PayerRequest toCreate =
        PayerRequest.builder()
            .name(name)
            .address(address)
            .address2(address2)
            .city(city)
            .state("NY")
            .zipcode(zipcode)
            .country("US")
            .phoneNumber(phoneNumber)
            .taxIdNumber(tin)
            .build();

    final AboundBulkResponse<Payer> response =
        getV2AboundClient().payers().create(Collections.singletonList(toCreate));
    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Payer> createdPayers = response.getData();
    assertThat(createdPayers).isNotNull().hasSize(1);

    final Payer created = createdPayers.get(0);
    assertThat(created).isNotNull();
    assertThat(created.getPayerId()).isEqualTo(TEST_PAYER_ID);
    assertThat(created.getName()).isEqualTo(name);
    assertThat(created.getAddress()).isEqualTo(address);
    assertThat(created.getAddress2()).isEqualTo(Optional.of(address2));
    assertThat(created.getCity()).isEqualTo(city);
    assertThat(created.getState()).isEqualTo("NY");
    assertThat(created.getZipcode()).isEqualTo(zipcode);
    assertThat(created.getCountry()).isEqualTo("US");
    assertThat(created.getPhoneNumber()).isEqualTo(phoneNumber);
    assertThat(created.getTaxIdNumber()).isNull();
    assertThat(created.getForeignId()).isEqualTo(Optional.of("your_foreign_id"));
  }

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<Payer> response = getAboundClient().payers().list();

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Payer> payers = response.getData();
    assertThat(payers).isNotNull().hasSize(1);
    final Payer payer = payers.get(0);
    PayerAssert.assertThat(payer).isHooli();
  }

  @Test
  public void testListV2() throws IOException {
    final AboundBulkResponse<Payer> response = getV2AboundClient().payers().list();

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Payer> payers = response.getData();
    assertThat(payers).isNotNull().hasSize(1);
    final Payer payer = payers.get(0);
    PayerAssert.assertThat(payer).isHooli();
  }

  @Test
  public void testListWithManyParams() throws IOException, InterruptedException {
    final String foreignId = TestUtils.randomAlphabetic();
    final String nextPage = TestUtils.randomAlphabetic();
    final PayerParams params = PayerParams.builder().foreignId(foreignId).page(nextPage).build();

    getMockAboundClient().payers().list(params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    assertThat(requestUrl.queryParameter("page")).isEqualTo(nextPage);
    assertThat(requestUrl.queryParameter("foreignId")).isEqualTo(foreignId);
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<Payer> response = getAboundClient().payers().retrieve(TEST_PAYER_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    final Payer payer = response.getData();
    PayerAssert.assertThat(payer).isHooli();
  }

  @Test
  public void testRetrieveV2() throws IOException {
    final AboundResponse<Payer> response = getV2AboundClient().payers().retrieve(TEST_PAYER_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    final Payer payer = response.getData();
    PayerAssert.assertThat(payer).isHooli();
  }

  @Test
  public void testUpdate() throws IOException {
    final String newPhoneNumber = TestUtils.randomNumberString(10);

    final PayerRequest toUpdate =
        PayerRequest.builder().country("CA").phoneNumber(newPhoneNumber).build();

    final AboundResponse<Payer> response =
        getAboundClient().payers().update(TEST_PAYER_ID, toUpdate);
    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Payer updated = response.getData();
    assertThat(updated.getName()).isEqualTo("Hooli");
    assertThat(updated.getAddress()).isEqualTo("1401 N Shoreline Blvd");
    assertThat(updated.getCountry()).isEqualTo("CA");
    assertThat(updated.getPhoneNumber()).isEqualTo(newPhoneNumber);
  }

  @Test
  public void testUpdateV2() throws IOException {
    final String newPhoneNumber = TestUtils.randomNumberString(10);

    final PayerRequest toUpdate =
        PayerRequest.builder().country("CA").phoneNumber(newPhoneNumber).build();

    final AboundResponse<Payer> response =
        getV2AboundClient().payers().update(TEST_PAYER_ID, toUpdate);
    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Payer updated = response.getData();
    assertThat(updated.getName()).isEqualTo("Hooli");
    assertThat(updated.getAddress()).isEqualTo("1401 N Shoreline Blvd");
    assertThat(updated.getCountry()).isEqualTo("CA");
    assertThat(updated.getPhoneNumber()).isEqualTo(newPhoneNumber);
  }

  @Test
  public void testDelete() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getAboundClient().payers().delete(TEST_PAYER_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    AboundResponseAssert.assertThat(response).hasEmptyDataObject();
  }

  @Test
  public void testDeleteV2() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getV2AboundClient().payers().delete(TEST_PAYER_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    AboundResponseAssert.assertThat(response).hasEmptyDataObject();
  }
}
