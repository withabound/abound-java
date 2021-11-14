package com.withabound.models.documents.account_statements;

import com.withabound.models.documents.DocumentRequest;
import com.withabound.models.documents.DocumentType;
import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class AccountStatementDocumentRequest extends DocumentRequest {
  private Integer year;

  private String beginDate; // YYYY-MM-DD. The date representing the start of this period.

  private String endDate; // YYYY-MM-DD. The date representing the end of this period.

  private String accountNumber;

  private AccountStatementDocumentSummary summary;

  private AccountStatementDocumentBank bank;

  private String disclosure;

  @Override
  public DocumentType getType() {
    return DocumentType.ACCOUNT_STATEMENT;
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
