package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.withabound.AbstractAboundTest;
import com.withabound.exceptions.AboundApiException;
import com.withabound.models.documents.Document;
import com.withabound.models.documents.DocumentParams;
import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.StateTaxInfo;
import com.withabound.models.documents.StateTaxInfoWithIncome;
import com.withabound.models.documents.ten99int.Form1099INTDocumentRequest;
import com.withabound.models.documents.ten99k.Form1099KDocumentRequest;
import com.withabound.models.documents.ten99k.GrossAmountsByMonth;
import com.withabound.models.documents.ten99k.PayerClassification;
import com.withabound.models.documents.ten99k.TransactionsReportedClassification;
import com.withabound.models.documents.ten99nec.Form1099NECDocumentRequest;
import com.withabound.models.documents.w9.ExemptFatcaCode;
import com.withabound.models.documents.w9.ExemptPayeeCode;
import com.withabound.models.documents.w9.FormW9DocumentRequest;
import com.withabound.models.documents.w9.W9TaxClassification;
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
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.RecordedRequest;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DocumentsTest extends AbstractAboundTest {
  public static final String TEST_DOCUMENT_ID =
      "documentId_testefbd5d3d9ee9526ef9ff89a7c6b879174170";

  public static final String PUBLIC_BANK_LOGO_URL =
      "https://www.chase.com/etc/designs/chase-ux/css/img/newheaderlogo.svg";

  @Test
  public void testCreate1099INT() throws IOException {
    final String accountNumber = TestUtils.randomNumberString(9);
    final String routingNumber = "102001017";
    final Double interestIncome = TestUtils.randomCurrencyAmount(1000);
    final Double earlyWithdrawalPenalty = TestUtils.randomCurrencyAmount();
    final Double usSavingsBondsInterest = TestUtils.randomCurrencyAmount(500);
    final Double investmentExpenses = TestUtils.randomCurrencyAmount(800);
    final Double foreignTaxPaid = TestUtils.randomCurrencyAmount(4000);
    final Double taxExemptInterest = TestUtils.randomCurrencyAmount();
    final Double specifiedPrivateActivityBondInterest = TestUtils.randomCurrencyAmount(2000);
    final Double marketDiscount = TestUtils.randomCurrencyAmount(300);
    final Double bondPremium = TestUtils.randomCurrencyAmount();
    final Double bondPremiumTreasury = TestUtils.randomCurrencyAmount();
    final Double bondPremiumTaxExemptBond = TestUtils.randomCurrencyAmount();

    final String userStateId = TestUtils.randomAlphabetic();

    final StateTaxInfo stateTaxInfo =
        StateTaxInfo.builder()
            .filingState("CA")
            .userStateId(userStateId)
            .stateTaxWithheld(0.00)
            .build();

    final Form1099INTDocumentRequest form1099INTDocumentRequest =
        Form1099INTDocumentRequest.builder()
            .year(2020)
            .payerId(PayersTest.TEST_PAYER_ID)
            .hasFatcaFilingRequirement(true)
            .accountNumber(accountNumber)
            .payersRoutingNumber(routingNumber)
            .interestIncome(interestIncome)
            .earlyWithdrawalPenalty(earlyWithdrawalPenalty)
            .usSavingsBondsInterest(usSavingsBondsInterest)
            .federalIncomeTaxWithheld(0.00)
            .investmentExpenses(investmentExpenses)
            .foreignTaxPaid(foreignTaxPaid)
            .foreignTaxPaidCountry("France")
            .taxExemptInterest(taxExemptInterest)
            .specifiedPrivateActivityBondInterest(specifiedPrivateActivityBondInterest)
            .marketDiscount(marketDiscount)
            .bondPremium(bondPremium)
            .bondPremiumTreasury(bondPremiumTreasury)
            .bondPremiumTaxExemptBond(bondPremiumTaxExemptBond)
            .stateTaxInfo(Collections.singletonList(stateTaxInfo))
            .build();

    final AboundBulkResponse<Document> response =
        getAboundClient()
            .documents()
            .create(TestUtils.TEST_USER_ID, Collections.singletonList(form1099INTDocumentRequest));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();
    final List<Document> created = response.getData();
    assertThat(created).isNotNull().hasSize(1);
    final Document createdDocument = created.get(0);
    assertThat(createdDocument).isNotNull();
    assertThat(createdDocument.getDocumentId().orElse(null)).isEqualTo(TEST_DOCUMENT_ID);
    assertThat(createdDocument.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_1099-INT.pdf");
    assertThat(createdDocument.getDocumentName()).isEqualTo("2020 Form 1099-INT");
    assertThat(createdDocument.getType()).isEqualTo(DocumentType.TEN99INT);
    assertThat(createdDocument.getYear()).isEqualTo("2020");
    assertThat(createdDocument.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
  }

  @Test
  public void testCreate1099K() throws IOException {
    final String pseName = TestUtils.randomAlphabetic();
    final String psePhoneNumber = TestUtils.randomNumberString(10);
    final Integer numberOfPaymentTransactions = TestUtils.randomInt(10_000);

    final Double january = TestUtils.randomCurrencyAmount();
    final GrossAmountsByMonth grossAmountsByMonth =
        GrossAmountsByMonth.builder().january(january).build();

    final StateTaxInfo stateTaxInfo = StateTaxInfo.builder().filingState("CA").build();

    final Form1099KDocumentRequest form1099KDocumentRequest =
        Form1099KDocumentRequest.builder()
            .year(2020)
            .payerId(PayersTest.TEST_PAYER_ID)
            .payerClassification(PayerClassification.PAYMENT_SETTLEMENT_ENTITY)
            .pseName(pseName)
            .psePhoneNumber(psePhoneNumber)
            .transactionsReportedClassification(TransactionsReportedClassification.PAYMENT_CARD)
            .aggregateGrossAmount(january)
            .numberOfPaymentTransactions(numberOfPaymentTransactions)
            .grossAmountsByMonth(grossAmountsByMonth)
            .stateTaxInfo(Collections.singletonList(stateTaxInfo))
            .build();

    final AboundBulkResponse<Document> response =
        getAboundClient()
            .documents()
            .create(TestUtils.TEST_USER_ID, Collections.singletonList(form1099KDocumentRequest));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();
    final List<Document> created = response.getData();
    assertThat(created).isNotNull().hasSize(1);
    final Document createdDocument = created.get(0);
    assertThat(createdDocument).isNotNull();
    assertThat(createdDocument.getDocumentId().orElse(null)).isEqualTo(TEST_DOCUMENT_ID);
    assertThat(createdDocument.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_1099-K.pdf");
    assertThat(createdDocument.getDocumentName()).isEqualTo("2020 Form 1099-K");
    assertThat(createdDocument.getType()).isEqualTo(DocumentType.TEN99K);
    assertThat(createdDocument.getYear()).isEqualTo("2020");
    assertThat(createdDocument.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
  }

  @Test
  public void testCreate1099KThrowsWhenMissingNumberOfPaymentTransactions() {
    final Double january = TestUtils.randomCurrencyAmount();
    final GrossAmountsByMonth grossAmountsByMonth =
        GrossAmountsByMonth.builder().january(january).build();

    final StateTaxInfo stateTaxInfo = StateTaxInfo.builder().filingState("CA").build();

    final Form1099KDocumentRequest form1099KDocumentRequest =
        Form1099KDocumentRequest.builder()
            .year(2020)
            .payerId(PayersTest.TEST_PAYER_ID)
            .payerClassification(PayerClassification.PAYMENT_SETTLEMENT_ENTITY)
            .pseName(TestUtils.randomAlphabetic())
            .psePhoneNumber(TestUtils.randomNumberString(10))
            .transactionsReportedClassification(TransactionsReportedClassification.PAYMENT_CARD)
            .aggregateGrossAmount(january)
            .grossAmountsByMonth(grossAmountsByMonth)
            .stateTaxInfo(Collections.singletonList(stateTaxInfo))
            .build();

    Assertions.assertThatThrownBy(
            () ->
                getAboundClient()
                    .documents()
                    .create(
                        TestUtils.TEST_USER_ID,
                        Collections.singletonList(form1099KDocumentRequest)))
        .isInstanceOf(AboundApiException.class)
        .hasMessage(
            "Expected numberOfPaymentTransactions to be of type number, but received undefined")
        .hasFieldOrPropertyWithValue("statusCode", 400)
        .hasFieldOrProperty("request");
  }

  @Test
  public void testCreateFormW9WithoutOptionalFields() throws IOException {
    final FormW9DocumentRequest toCreate =
        FormW9DocumentRequest.builder()
            .year(2020)
            .taxClassification(W9TaxClassification.SOLE_PROPRIETOR)
            .certificationTimestamp((int) System.currentTimeMillis())
            .build();

    final AboundBulkResponse<Document> response =
        getAboundClient()
            .documents()
            .create(TestUtils.TEST_USER_ID, Collections.singletonList(toCreate));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();
    assertThat(response.getData()).isNotNull().hasSize(1);

    final Document created = response.getData().get(0);
    assertThat(created).isNotNull();
    assertThat(created.getDocumentId().orElse(null)).isEqualTo(TEST_DOCUMENT_ID);
    assertThat(created.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_W-9.pdf");
    assertThat(created.getDocumentName()).isEqualTo("2020 Form W-9");
    assertThat(created.getType()).isEqualTo(DocumentType.W9);
    assertThat(created.getYear()).isEqualTo("2020");
    assertThat(created.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
  }

  @Test
  public void testCreateFormW9WithOptionalFields() throws IOException {
    final String accountNumber = TestUtils.randomNumberString(9);

    final FormW9DocumentRequest toCreate =
        FormW9DocumentRequest.builder()
            .payerId(PayersTest.TEST_PAYER_ID)
            .year(2020)
            .taxClassification(W9TaxClassification.SOLE_PROPRIETOR)
            .exemptPayeeCode(ExemptPayeeCode.ONE)
            .exemptFatcaCode(ExemptFatcaCode.A)
            .certificationTimestamp((int) System.currentTimeMillis())
            .accountNumbers(Collections.singletonList(accountNumber))
            .build();

    final AboundBulkResponse<Document> response =
        getAboundClient()
            .documents()
            .create(TestUtils.TEST_USER_ID, Collections.singletonList(toCreate));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();
    assertThat(response.getData()).isNotNull().hasSize(1);

    final Document created = response.getData().get(0);
    assertThat(created).isNotNull();
    assertThat(created.getDocumentId().orElse(null)).isEqualTo(TEST_DOCUMENT_ID);
    assertThat(created.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_W-9.pdf");
    assertThat(created.getDocumentName()).isEqualTo("2020 Form W-9");
    assertThat(created.getType()).isEqualTo(DocumentType.W9);
    assertThat(created.getYear()).isEqualTo("2020");
    assertThat(created.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
  }

  //   @Test
  //   public void testCreate1099MISCWithoutOptionalFields() throws IOException {
  //     final Form1099MISCDocumentRequest toCreate =
  //         Form1099MISCDocumentRequest.builder()
  //             .payerId(PayersTest.TEST_PAYER_ID)
  //             .rents(12121.75)
  //             .year(2021)
  //             .build();

  //     final AboundBulkResponse<Document> response =
  //         getAboundClient()
  //             .documents()
  //             .create(TestUtils.TEST_USER_ID, Collections.singletonList(toCreate));

  //     AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();
  //     final List<Document> created = response.getData();
  //     assertThat(created).isNotNull().hasSize(1);
  //     final Document createdDocument = created.get(0);
  //     assertThat(createdDocument).isNotNull();
  //     assertThat(createdDocument.getDocumentId().orElse(null)).isEqualTo(TEST_DOCUMENT_ID);
  //     assertThat(createdDocument.getDocumentURL().orElse(null))
  //         .startsWith(
  //
  // "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_1099-MISC.pdf");
  //     assertThat(createdDocument.getDocumentName()).isEqualTo("2021 Form 1099-MISC");
  //     assertThat(createdDocument.getType()).isEqualTo(DocumentType.TEN99MISC);
  //     assertThat(createdDocument.getYear()).isEqualTo("2021");
  //     assertThat(createdDocument.getCreatedTimestamp())
  //         .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
  //   }

  //   @Test
  //   public void testCreate1099MISCWithOptionalFields() throws IOException {
  //     final Double rents = TestUtils.randomDouble(8000);
  //     final Double royalties = TestUtils.randomDouble(1000);
  //     final Double otherIncome = TestUtils.randomDouble(8000);
  //     final Double fishingBoatProceeds = TestUtils.randomDouble();
  //     final Double medicalPayments = TestUtils.randomDouble(1500);
  //     final Double substitutePayments = TestUtils.randomDouble(2000);
  //     final Double cropInsuranceProceeds = TestUtils.randomDouble(6000);
  //     final Double grossProceedsAttorney = TestUtils.randomDouble(4000);
  //     final Double fishPurchasedForResale = TestUtils.randomDouble(800);
  //     final Double section409ADeferrals = TestUtils.randomDouble(7000);
  //     final Double excessGoldenParachutePayments = TestUtils.randomDouble(14000);
  //     final Double nqdc = TestUtils.randomDouble(10000);

  //     final String payerStateId = TestUtils.randomAlphabetic();
  //     final Double stateIncome = TestUtils.randomDouble(20_000);

  //     final StateTaxInfoWithIncome stateTaxInfo =
  //         StateTaxInfoWithIncome.builder()
  //             .filingState("ca")
  //             .payerStateId(payerStateId)
  //             .stateTaxWithheld(0.00)
  //             .stateIncome(stateIncome)
  //             .build();

  //     final Form1099MISCDocumentRequest toCreate =
  //         Form1099MISCDocumentRequest.builder()
  //             .payerId(PayersTest.TEST_PAYER_ID)
  //             .year(2021)
  //             .hasFatcaFilingRequirement(false)
  //             .rents(rents)
  //             .royalties(royalties)
  //             .otherIncome(otherIncome)
  //             .federalIncomeTaxWithheld(0.00)
  //             .fishingBoatProceeds(fishingBoatProceeds)
  //             .medicalPayments(medicalPayments)
  //             .hasDirectSalesOver5000(false)
  //             .substitutePayments(substitutePayments)
  //             .cropInsuranceProceeds(cropInsuranceProceeds)
  //             .grossProceedsAttorney(grossProceedsAttorney)
  //             .fishPurchasedForResale(fishPurchasedForResale)
  //             .section409ADeferrals(section409ADeferrals)
  //             .excessGoldenParachutePayments(excessGoldenParachutePayments)
  //             .nqdc(nqdc)
  //             .stateTaxInfo(Collections.singletonList(stateTaxInfo))
  //             .build();

  //     final AboundBulkResponse<Document> response =
  //         getAboundClient()
  //             .documents()
  //             .create(TestUtils.TEST_USER_ID, Collections.singletonList(toCreate));

  //     AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();
  //     final List<Document> created = response.getData();
  //     assertThat(created).isNotNull().hasSize(1);
  //     final Document createdDocument = created.get(0);
  //     assertThat(createdDocument).isNotNull();
  //     assertThat(createdDocument.getDocumentId().orElse(null)).isEqualTo(TEST_DOCUMENT_ID);
  //     assertThat(createdDocument.getDocumentURL().orElse(null))
  //         .startsWith(
  //
  // "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_1099-MISC.pdf");
  //     assertThat(createdDocument.getDocumentName()).isEqualTo("2021 Form 1099-MISC");
  //     assertThat(createdDocument.getType()).isEqualTo(DocumentType.TEN99MISC);
  //     assertThat(createdDocument.getYear()).isEqualTo("2021");
  //     assertThat(createdDocument.getCreatedTimestamp())
  //         .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
  //   }

  @Test
  public void testCreate1099NEC() throws IOException {
    final String accountNumber = TestUtils.randomNumberString(9);
    final Double nonemployeeCompensation = TestUtils.randomDouble(4000);

    final String payerStateId = TestUtils.randomAlphabetic();
    final Double stateIncome = TestUtils.randomDouble(20_000);

    final StateTaxInfoWithIncome stateTaxInfo =
        StateTaxInfoWithIncome.builder()
            .filingState("CA")
            .payerStateId(payerStateId)
            .stateTaxWithheld(0.00)
            .stateIncome(stateIncome)
            .build();

    final Form1099NECDocumentRequest toCreate =
        Form1099NECDocumentRequest.builder()
            .payerId(PayersTest.TEST_PAYER_ID)
            .accountNumber(accountNumber)
            .nonemployeeCompensation(nonemployeeCompensation)
            .year(2020)
            .stateTaxInfo(Collections.singletonList(stateTaxInfo))
            .build();

    final AboundBulkResponse<Document> response =
        getAboundClient()
            .documents()
            .create(TestUtils.TEST_USER_ID, Collections.singletonList(toCreate));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();
    final List<Document> created = response.getData();
    assertThat(created).isNotNull().hasSize(1);
    final Document createdDocument = created.get(0);
    assertThat(createdDocument).isNotNull();
    assertThat(createdDocument.getDocumentId().orElse(null)).isEqualTo(TEST_DOCUMENT_ID);
    assertThat(createdDocument.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_1099-NEC.pdf");
    assertThat(createdDocument.getDocumentName()).isEqualTo("2020 Form 1099-NEC");
    assertThat(createdDocument.getType()).isEqualTo(DocumentType.TEN99NEC);
    assertThat(createdDocument.getYear()).isEqualTo("2020");
    assertThat(createdDocument.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));
  }

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<Document> response =
        getAboundClient().documents().list(TestUtils.TEST_USER_ID);

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Document> documents = response.getData();
    assertThat(documents).isNotNull().hasSize(1);

    final Document document = documents.get(0);
    DocumentAssert.assertThat(document).isFormW9();
  }

  @Test
  public void testListWithParams() throws IOException, InterruptedException {
    final String year = "2018";
    final String nextPage = TestUtils.randomAlphabetic();

    final DocumentParams params = DocumentParams.builder().year(year).page(nextPage).build();

    getMockAboundClient().documents().list(TestUtils.TEST_USER_ID, params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    final String requestUrlStr = requestUrl.toString();
    assertThat(requestUrlStr).endsWith(String.format("?year=2018&page=%s", nextPage));
  }

  @Test
  @Disabled(
      "Using the test credentials, there's a bug where documents.list() returns one document, but documents.retrieve(id) using that same documentId returns a 404. This test is also skipped in the Node SDK for the same reason.")
  public void testRetrieve() throws IOException {
    final AboundResponse<Document> response =
        getAboundClient().documents().retrieve(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    final Document retrieved = response.getData();
    DocumentAssert.assertThat(retrieved).isFormW9();
  }

  @Test
  public void testDelete() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getAboundClient().documents().delete(TestUtils.TEST_USER_ID, TEST_DOCUMENT_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();
    AboundResponseAssert.assertThat(response).hasEmptyDataObject();
  }
}
