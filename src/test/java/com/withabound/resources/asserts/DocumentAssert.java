package com.withabound.resources.asserts;

import com.withabound.models.documents.Document;
import com.withabound.models.documents.DocumentType;
import com.withabound.resources.DocumentsTest;
import java.time.Year;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;

public class DocumentAssert extends AbstractAssert<DocumentAssert, Document> {
  public static DocumentAssert assertThat(final Document actual) {
    return new DocumentAssert(actual);
  }

  public DocumentAssert isFormW9() {
    Assertions.assertThat(actual).isNotNull();
    Assertions.assertThat(actual.getType()).isEqualTo(DocumentType.W9);
    Assertions.assertThat(actual.getDocumentId().orElse(null))
        .isEqualTo(DocumentsTest.TEST_DOCUMENT_ID);
    Assertions.assertThat(actual.getDocumentURL().orElse(null))
        .startsWith(
            "https://tax-documents-sandbox.s3.us-west-2.amazonaws.com/test62ae93bafa6310aa9952e8b3bf5796443111/2021_Form_W-9.pdf");
    final String year = String.valueOf(Year.now().getValue());
    Assertions.assertThat(actual.getDocumentName()).isEqualTo(year + " Form W-9");
    Assertions.assertThat(actual.getYear()).isEqualTo(year);
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
