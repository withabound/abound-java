package com.withabound.resources.asserts;

import com.withabound.models.documents.Document;
import com.withabound.models.documents.DocumentType;
import com.withabound.resources.DocumentsTest;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

public class DocumentAssert extends AbstractAssert<DocumentAssert, Document> {
  public static DocumentAssert assertThat(final Document actual) {
    return new DocumentAssert(actual);
  }

  public DocumentAssert is7890AccountStatement() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getType()).isEqualTo(DocumentType.ACCOUNT_STATEMENT);
    Assertions.assertThat(actual.getDocumentId().orElse(null))
        .isEqualTo(DocumentsTest.TEST_DOCUMENT_ID);
    Assertions.assertThat(actual.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2020-01-01_2020-01-31_Account_Statement_7890.pdf");
    Assertions.assertThat(actual.getDocumentName())
        .isEqualTo("2020-01-01 - 2020-01-31 Account Statement (7890)");
    Assertions.assertThat(actual.getYear()).isEqualTo("2020");
    Assertions.assertThat(actual.getStatus()).isEmpty();
    Assertions.assertThat(actual.getMessage()).isEmpty();
    Assertions.assertThat(actual.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));

    return this;
  }

  public DocumentAssert isFormW9() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getType()).isEqualTo(DocumentType.W9);
    Assertions.assertThat(actual.getDocumentId().orElse(null))
        .isEqualTo(DocumentsTest.TEST_DOCUMENT_ID);
    Assertions.assertThat(actual.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_W-9.pdf");
    Assertions.assertThat(actual.getDocumentName()).isEqualTo("2023 Form W-9");
    Assertions.assertThat(actual.getYear()).isEqualTo("2023");
    Assertions.assertThat(actual.getStatus()).isEmpty();
    Assertions.assertThat(actual.getMessage()).isEmpty();
    Assertions.assertThat(actual.getCreatedTimestamp())
        .isCloseTo(System.currentTimeMillis(), Offset.offset(30000L));

    return this;
  }

  private DocumentAssert(final Document actual) {
    super(actual, DocumentAssert.class);
  }
}
