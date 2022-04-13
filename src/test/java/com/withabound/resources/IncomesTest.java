package com.withabound.resources;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.withabound.AbstractAboundTest;
import com.withabound.models.incomes.Income;
import com.withabound.models.incomes.IncomeDocumentType;
import com.withabound.models.incomes.IncomeParams;
import com.withabound.models.incomes.IncomeRequest;
import com.withabound.models.incomes.IncomeType;
import com.withabound.resources.asserts.AboundBulkResponseAssert;
import com.withabound.resources.asserts.AboundResponseAssert;
import com.withabound.resources.asserts.IncomeAssert;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import com.withabound.util.TestUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

public class IncomesTest extends AbstractAboundTest {
  public static final String TEST_INCOME_ID = "incomeId_test8cb0d56b942722b6d719fa5aa9c5a8dbaa0f";
  public static final String TEST_INCOME_ID_2 = "incomeId_teste8c6bf02953ca4f2691e05ce98138c50a56a";

  @Test
  public void testList() throws IOException {
    final AboundBulkResponse<Income> response =
        getAboundClient().incomes().list(TestUtils.TEST_USER_ID);

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Income> listed = response.getData();
    assertThat(listed).hasSize(2);

    final Income first = listed.get(0);
    IncomeAssert.assertThat(first).isDesignServicesClientInvoice();

    final Income second = listed.get(1);
    IncomeAssert.assertThat(second).isDesignServicesSSA1099ClientInvoice();
  }

  @Test
  public void testListWithNextPageParam() throws IOException, InterruptedException {
    final String nextPage = TestUtils.randomAlphabetic();
    final IncomeParams params = IncomeParams.builder().page(nextPage).build();

    getMockAboundClient().incomes().list(TestUtils.TEST_USER_ID, params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    assertThat(requestUrl.queryParameter("page")).isEqualTo(nextPage);
    assertThat(requestUrl.queryParameter("foreignId")).isNull();
    assertThat(requestUrl.queryParameter("incomeType")).isNull();
  }

  @Test
  public void testListWithManyParams() throws IOException, InterruptedException {
    final String nextPage = TestUtils.randomAlphabetic();
    final String foreignId = TestUtils.randomAlphabetic();
    final IncomeType w2 = IncomeType.W2;
    final IncomeParams params =
        IncomeParams.builder().page(nextPage).foreignId(foreignId).incomeType(w2).build();

    getMockAboundClient().incomes().list(TestUtils.TEST_USER_ID, params);

    final RecordedRequest recordedRequest = getMockAboundServer().takeRequest();
    final HttpUrl requestUrl = recordedRequest.getRequestUrl();
    assertThat(requestUrl).isNotNull();
    assertThat(requestUrl.queryParameter("page")).isEqualTo(nextPage);
    assertThat(requestUrl.queryParameter("foreignId")).isEqualTo(foreignId);
    assertThat(requestUrl.queryParameter("incomeType")).isEqualTo("w2");
  }

  @Test
  public void testCreate() throws IOException {
    final IncomeRequest ten99 =
        IncomeRequest.builder()
            .incomeType(IncomeType.TEN99)
            .amount(123.45)
            .date("2020-01-01")
            .category("1099 income")
            .description("1099 description")
            .foreignId("1099_foreign_id")
            .build();

    final IncomeRequest ten99Int =
        IncomeRequest.builder()
            .incomeType(IncomeType.TEN99)
            .amount(444.77)
            .date("2020-02-01")
            .category("1099-INT income")
            .description("1099-INT description")
            .foreignId("1099int_foreign_id")
            .documentType(IncomeDocumentType.TEN99INT)
            .build();

    final IncomeRequest w2 =
        IncomeRequest.builder()
            .incomeType(IncomeType.W2)
            .amount(32000.99)
            .date("2020-03-01")
            .category("W2 income")
            .description("W2 description")
            .foreignId("w2_foreign_id")
            .build();

    final IncomeRequest personal =
        IncomeRequest.builder()
            .incomeType(IncomeType.PERSONAL)
            .amount(999.10)
            .date("2020-04-01")
            .category("Personal income")
            .description("Personal description")
            .foreignId("personal_foreign_id")
            .build();

    final IncomeRequest noIncomeType =
        IncomeRequest.builder()
            .amount(101.10)
            .date("2020-05-01")
            .category("Personal income")
            .description("Personal description")
            .foreignId("personal_foreign_id")
            .build();

    final AboundBulkResponse<Income> response =
        getAboundClient()
            .incomes()
            .create(
                TestUtils.TEST_USER_ID, Arrays.asList(ten99, ten99Int, w2, personal, noIncomeType));

    AboundBulkResponseAssert.assertThat(response).hasResponseMetadata();

    final List<Income> created = response.getData();
    assertThat(created).hasSize(5);

    final Income createdTen99 = created.get(0);
    assertThat(createdTen99).isNotNull();
    assertThat(createdTen99.getIncomeId()).isNotEmpty();
    assertThat(createdTen99.getIncomeType()).isEqualTo(ten99.getIncomeType());
    assertThat(createdTen99.getAmount()).isEqualTo(ten99.getAmount());
    assertThat(createdTen99.getDate()).isEqualTo(ten99.getDate());
    assertThat(createdTen99.getDescription()).isEqualTo(ten99.getDescription());
    assertThat(createdTen99.getCategory()).isEqualTo(ten99.getCategory());
    assertThat(createdTen99.getForeignId()).isEqualTo(ten99.getForeignId());

    final Income createdTen99Int = created.get(1);
    assertThat(createdTen99Int).isNotNull();
    assertThat(createdTen99Int.getIncomeId()).isNotEmpty();
    // the commented assertions below are due to the nature of some Incomes created with test
    // credentials having some incorrect properties assigned in response bodies. This issue
    // only exists with the test credentials.

    // assertThat(createdTen99Int.getIncomeType()).isEqualTo(IncomeType.TEN99INT);
    assertThat(createdTen99Int.getAmount()).isEqualTo(ten99Int.getAmount());
    assertThat(createdTen99Int.getDate()).isEqualTo(ten99Int.getDate());
    assertThat(createdTen99Int.getDescription()).isEqualTo(ten99Int.getDescription());
    assertThat(createdTen99Int.getCategory()).isEqualTo(ten99Int.getCategory());
    assertThat(createdTen99Int.getForeignId()).isEqualTo(ten99Int.getForeignId());
    // assertThat(createdTen99Int.getDocumentType()).isEmpty();

    final Income createdW2 = created.get(2);
    assertThat(createdW2).isNotNull();
    assertThat(createdW2.getIncomeId()).isNotEmpty();
    // assertThat(createdW2.getIncomeType()).isEqualTo(IncomeType.W2);
    assertThat(createdW2.getAmount()).isEqualTo(w2.getAmount());
    assertThat(createdW2.getDate()).isEqualTo(w2.getDate());
    assertThat(createdW2.getDescription()).isEqualTo(w2.getDescription());
    assertThat(createdW2.getCategory()).isEqualTo(w2.getCategory());
    assertThat(createdW2.getForeignId()).isEqualTo(w2.getForeignId());
    assertThat(createdW2.getDocumentType()).isEmpty();

    final Income createdPersonal = created.get(3);
    assertThat(createdPersonal).isNotNull();
    assertThat(createdPersonal.getIncomeId()).isNotEmpty();
    assertThat(createdPersonal.getIncomeType()).isEqualTo(personal.getIncomeType());
    assertThat(createdPersonal.getAmount()).isEqualTo(personal.getAmount());
    assertThat(createdPersonal.getDate()).isEqualTo(personal.getDate());
    assertThat(createdPersonal.getDescription()).isEqualTo(personal.getDescription());
    assertThat(createdPersonal.getCategory()).isEqualTo(personal.getCategory());
    assertThat(createdPersonal.getForeignId()).isEqualTo(personal.getForeignId());
    assertThat(createdPersonal.getDocumentType()).isEmpty();

    final Income createNoIncomeType = created.get(4);
    assertThat(createNoIncomeType).isNotNull();
    assertThat(createNoIncomeType.getIncomeId()).isNotEmpty();
    assertThat(createNoIncomeType.getAmount()).isEqualTo(noIncomeType.getAmount());
  }

  @Test
  public void testNotesString() throws IOException {
    final IncomeRequest income =
        IncomeRequest.builder()
            .incomeType(IncomeType.TEN99)
            .amount(123.45)
            .date("2020-01-01")
            .category("1099 income")
            .description(TestUtils.randomAlphabetic())
            // test the builder
            .notes("hello world")
            .build();

    final Income created =
        getAboundClient()
            .incomes()
            .create(TestUtils.TEST_USER_ID, Collections.singletonList(income))
            .getData()
            .get(0);

    assertThat(created.getNotes()).isPresent();
    final JsonElement notes = created.getNotes().get();
    assertThat(notes.isJsonPrimitive()).isTrue();
    assertThat(notes.isJsonObject()).isFalse();
    assertThat(notes.getAsString()).isEqualTo("hello world");
  }

  @Test
  public void testNotesJsonObject() throws IOException {
    final JsonObject notes = new JsonObject();
    notes.addProperty("hello", "world");
    notes.addProperty("id", 101);

    final IncomeRequest income =
        IncomeRequest.builder()
            .incomeType(IncomeType.TEN99)
            .amount(123.45)
            .date("2020-01-01")
            .category("1099 income")
            .description(TestUtils.randomAlphabetic())
            .build();

    // test the setter
    income.setNotes(notes);

    final Income created =
        getAboundClient()
            .incomes()
            .create(TestUtils.TEST_USER_ID, Collections.singletonList(income))
            .getData()
            .get(0);

    assertThat(created.getNotes()).isPresent();
    final JsonElement createdNotes = created.getNotes().get();
    assertThat(createdNotes.isJsonPrimitive()).isFalse();
    assertThat(createdNotes.isJsonObject()).isTrue();
    assertThat(createdNotes.getAsJsonObject().size()).isEqualTo(2);
    assertThat(createdNotes.getAsJsonObject().get("hello").getAsString()).isEqualTo("world");
    assertThat(createdNotes.getAsJsonObject().get("id").getAsInt()).isEqualTo(101);
  }

  @Test
  public void testRetrieve() throws IOException {
    final AboundResponse<Income> response =
        getAboundClient().incomes().retrieve(TestUtils.TEST_USER_ID, TEST_INCOME_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Income retrieved = response.getData();

    IncomeAssert.assertThat(retrieved).isDesignServicesClientInvoice();
  }

  @Test
  public void testUpdate() throws IOException {
    final String description = TestUtils.randomAlphabetic();
    final String date = TestUtils.randomDate();
    final String category = TestUtils.randomAlphabetic();
    final Double amount = TestUtils.randomCurrencyAmount();

    final IncomeRequest toUpdate =
        IncomeRequest.builder()
            .description(description)
            .date(date)
            .category(category)
            .amount(amount)
            .build();

    final AboundResponse<Income> response =
        getAboundClient().incomes().update(TestUtils.TEST_USER_ID, TEST_INCOME_ID, toUpdate);

    AboundResponseAssert.assertThat(response).hasResponseMetadata();

    final Income updated = response.getData();

    assertThat(updated).isNotNull();
    assertThat(updated.getIncomeId()).isEqualTo(TEST_INCOME_ID);
    assertThat(updated.getDescription()).isEqualTo(description);
    assertThat(updated.getDate()).isEqualTo(date);
    assertThat(updated.getCategory()).isEqualTo(Optional.of(category));
    assertThat(updated.getAmount()).isEqualTo(amount);
    assertThat(updated.getIncomeType()).isEqualTo(Optional.of(IncomeType.TEN99));
  }

  @Test
  public void testDelete() throws IOException {
    final AboundResponse<EmptyJsonObject> response =
        getAboundClient().incomes().delete(TestUtils.TEST_USER_ID, TEST_INCOME_ID);

    AboundResponseAssert.assertThat(response).hasResponseMetadata().hasEmptyDataObject();
  }
}
