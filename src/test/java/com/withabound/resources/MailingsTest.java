package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.models.mailings.Mailing;
import com.withabound.models.mailings.MailingParams;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.MailingAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import com.withabound.util.TestUtils;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

public class MailingsTest extends AbstractAboundTest {
  public static final String TEST_DOCUMENT_ID =
      "documentId_testefbd5d3d9ee9526ef9ff89a7c6b879174170";
  public static final String TEST_MAILING_ID = "mailingId_testa4824d994d97be802ad48729fb1e7e169648";

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<Mailing> listed =
        getAboundClient().mailings().list(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID);

    AboundBulkResponseAssert.assertThat(listed).hasResponseMetadata();

    assertThat(listed.getData()).hasSize(1);

    final Mailing mailing = listed.getData().get(0);
    MailingAssert.assertThat(mailing).isValidMailing();
  }

  @Test
  public void testListV2() throws IOException {
    final AboundBulkResponse<Mailing> listed =
        getV2AboundClient().mailings().list(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID);

    AboundBulkResponseAssert.assertThat(listed).hasResponseMetadata();

    assertThat(listed.getData()).hasSize(1);

    final Mailing mailing = listed.getData().get(0);
    MailingAssert.assertThat(mailing).isValidMailing();
  }

  @Test
  public void testListWithManyParams() throws IOException, InterruptedException {
    final String nextPage = TestUtils.randomAlphabetic();
    final MailingParams params = MailingParams.builder().page(nextPage).build();

    getMockAboundClient().mailings().list(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID, params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    assertThat(requestUrl.queryParameter("page")).isEqualTo(nextPage);
  }

  @Test
  public void testCreate() throws IOException {
    final AboundResponse<Mailing> created =
        getAboundClient().mailings().create(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID);

    AboundResponseAssert.assertThat(created).hasResponseMetadata();

    final Mailing createdMailing = created.getData();
    MailingAssert.assertThat(createdMailing).isValidMailing();
  }

  @Test
  public void testCreateV2() throws IOException {
    final AboundResponse<Mailing> created =
        getV2AboundClient().mailings().create(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID);

    AboundResponseAssert.assertThat(created).hasResponseMetadata();

    final Mailing createdMailing = created.getData();
    MailingAssert.assertThat(createdMailing).isValidMailing();
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<Mailing> response =
        getAboundClient()
            .mailings()
            .retrieve(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID, TEST_MAILING_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Mailing retrieved = response.getData();

    MailingAssert.assertThat(retrieved).isValidMailing();
  }

  @Test
  public void testRetrieveV2() throws IOException {
    final AboundResponse<Mailing> response =
        getV2AboundClient()
            .mailings()
            .retrieve(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID, TEST_MAILING_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Mailing retrieved = response.getData();

    MailingAssert.assertThat(retrieved).isValidMailing();
  }

  @Test
  public void testDelete() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getAboundClient()
            .mailings()
            .delete(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID, TEST_MAILING_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata().hasEmptyDataObject();
  }

  @Test
  public void testDeleteV2() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getV2AboundClient()
            .mailings()
            .delete(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID, TEST_MAILING_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata().hasEmptyDataObject();
  }
}
