package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.models.documents.Document;
import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.account_statements.AccountStatementDocumentBank;
import com.withabound.models.documents.account_statements.AccountStatementDocumentRequest;
import com.withabound.models.documents.account_statements.AccountStatementDocumentSummary;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.DocumentAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DocumentsTest extends AbstractAboundTest {
  public static final String TEST_DOCUMENT_ID =
      "documentId_testefbd5d3d9ee9526ef9ff89a7c6b879174170";

  public static final String PUBLIC_BANK_LOGO_URL =
      "https://www.chase.com/etc/designs/chase-ux/css/img/newheaderlogo.svg";

  @Test
  public void testCreate() throws IOException {
    final String accountNumber = TestUtils.randomNumberString(9);
    final String last4 = accountNumber.substring(accountNumber.length() - 4);

    final AccountStatementDocumentBank.CustomerService customerService =
        AccountStatementDocumentBank.CustomerService.builder()
            .phoneNumber("555-555-5555")
            .email(TestUtils.randomEmail())
            .website("www.example.com")
            .build();

    final AccountStatementDocumentBank bank =
        AccountStatementDocumentBank.builder()
            .name(TestUtils.randomAlphabetic())
            .logo(PUBLIC_BANK_LOGO_URL)
            .address(TestUtils.randomAlphabetic())
            .city(TestUtils.randomAlphabetic())
            .state("CA")
            .zipcode(TestUtils.randomNumberString(5))
            .customerService(customerService)
            .build();

    final AccountStatementDocumentSummary summary =
        AccountStatementDocumentSummary.builder()
            .beginningBalance(1234.89)
            .endingBalance(4321.89)
            .interestPercentage(1.23)
            .interestAmount(6.78)
            .totalFees(5.25)
            .build();

    final AccountStatementDocumentRequest accountStatementDocumentRequest =
        AccountStatementDocumentRequest.builder()
            .year(2020)
            .beginDate("2020-03-01")
            .endDate("2020-05-31")
            .accountNumber(accountNumber)
            .bank(bank)
            .summary(summary)
            .build();

    final AboundBulkResponse<Document> response =
        getAboundClient()
            .documents()
            .create(
                TestUtils.TEST_USER_ID, Collections.singletonList(accountStatementDocumentRequest));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Document> createdDocuments = response.getData();
    assertThat(createdDocuments).isNotNull().hasSize(1);

    final Document createdDocument = createdDocuments.get(0);
    assertThat(createdDocument).isNotNull();
    assertThat(createdDocument.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(1000L));
    assertThat(createdDocument.getDocumentId().orElse(null)).isEqualTo(TEST_DOCUMENT_ID);
    assertThat(createdDocument.getDocumentName())
        .isEqualTo(String.format("2020-03-01 - 2020-05-31 Account Statement (%s)", last4));
    assertThat(createdDocument.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2020-01-01_2020-01-31_Account_Statement_7890.pdf");
    assertThat(createdDocument.getType()).isEqualTo(DocumentType.ACCOUNT_STATEMENT);
    assertThat(createdDocument.getYear()).isEqualTo("2020");
  }

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<Document> response =
        getAboundClient().documents().list(TestUtils.TEST_USER_ID);

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Document> documents = response.getData();
    assertThat(documents).isNotNull().hasSize(1);

    final Document document = documents.get(0);
    DocumentAssert.assertThat(document).is7890AccountStatement();
  }

  @Test
  @Disabled(
      "Using the test credentials, there's a bug where documents.list() returns one document, but documents.retrieve(id) using that same documentId returns a 404. This test is also skipped in the Node SDK for the same reason.")
  public void testRetrieve() throws IOException {
    final AboundResponse<Document> response =
        getAboundClient().documents().retrieve(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    DocumentAssert.assertThat(response.getData()).is7890AccountStatement();
  }

  @Test
  public void testDelete() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getAboundClient().documents().delete(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    AboundResponseAssert.assertThat(response).hasEmptyDataObject();
  }
}
