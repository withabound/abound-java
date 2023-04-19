package com.withabound.models.documents.account_statements;

import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.IDocumentRequest;
import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

/**
 * See https://docs.withabound.com/reference/documents
 *
 * @deprecated Our v2 API is now deprecated and will become completely unavailable on Tuesday May
 *     16, 2023. Please consider upgrading to our v3 API as a way to prepare for the sunsetting of
 *     v2. For more detail on these product changes, what endpoints are changing in v3 and how that
 *     may affect your company, please view our <a href="https://docs.withabound.com/changelog">API
 *     Changelog</a>.
 */
@Deprecated
@Builder
@Setter
public class AccountStatementDocumentRequest implements IDocumentRequest {
  private final DocumentType type = DocumentType.ACCOUNT_STATEMENT;

  private Integer year;

  private String beginDate; // YYYY-MM-DD. The date representing the start of this period.

  private String endDate; // YYYY-MM-DD. The date representing the end of this period.

  private String accountNumber;

  private AccountStatementDocumentSummary summary;

  private AccountStatementDocumentBank bank;

  private String disclosure;

  public DocumentType getType() {
    return type;
  }

  public Integer getYear() {
    return year;
  }

  public String getBeginDate() {
    return beginDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public AccountStatementDocumentSummary getSummary() {
    return summary;
  }

  public AccountStatementDocumentBank getBank() {
    return bank;
  }

  public Optional<String> getDisclosure() {
    return Optional.ofNullable(disclosure);
  }
}
