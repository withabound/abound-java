package com.withabound.models.documents.ten99k;

import com.withabound.models.documents.DocumentRequest;
import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.StateTaxInfo;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Form1099KDocumentRequest extends DocumentRequest {
  /** The payer filing or issuing this form. */
  private String payerId;

  /** The tax year associated with this form. */
  private int year;

  /** The type of payer. */
  private PayerClassification payerClassification;

  /** The type of transactions reported. */
  private TransactionsReportedClassification transactionsReportedClassification;

  /**
   * The payment settlement entity name. Required if {@link
   * Form1099KDocumentRequest#payerClassification} is {@link
   * PayerClassification#PAYMENT_SETTLEMENT_ENTITY}.
   */
  private String pseName;

  /**
   * The payment settlement entity phone number. Required if {@link
   * Form1099KDocumentRequest#payerClassification} is {@link
   * PayerClassification#PAYMENT_SETTLEMENT_ENTITY}.
   */
  private String psePhoneNumber;

  /** The account number or other unique number the PSE assigned to distinguish an account. */
  private String accountNumber;

  /**
   * Aggregate gross amount of payment card/third party network transactions made to you through the
   * PSE during the calendar year.
   */
  private Double aggregateGrossAmount;

  /**
   * The aggregate gross amount of all reportable payment transactions made to you through the PSE
   * during the calendar year where the card was not present at the time of the transaction or the
   * card number was keyed into the terminal. Typically, this relates to online sales, phone sales,
   * or catalogue sales. If {@link Form1099KDocumentRequest#transactionsReportedClassification} is
   * {@link TransactionsReportedClassification#THIRD_PARTY_NETWORK}, or if these are third party
   * network transactions, card not present transactions will not be reported.
   */
  private Double aggregateGrossAmountCardNotPresent;

  /**
   * Payment brands use merchant category codes (MCCs) to classify merchants and businesses by the
   * type of goods or services provided.
   */
  private String merchantCategoryCode;

  /**
   * The number of payment transactions (not including refund transactions) processed through the
   * payment card/third party network.
   */
  private int numberOfPaymentTransactions;

  /**
   * The federal income tax withheld. Generally, a payer must backup withhold if you did not furnish
   * your TIN or you did not furnish the correct TIN to the payer.
   */
  private Double federalIncomeTaxWithheld;

  private GrossAmountsByMonth grossAmountsByMonth;

  /** Up to two (2) state tax information objects. */
  private List<StateTaxInfo> stateTaxInfo;

  @Override
  public DocumentType getType() {
    return DocumentType.TEN99K;
  }

  public String getPayerId() {
    return payerId;
  }

  public int getYear() {
    return year;
  }

  public PayerClassification getPayerClassification() {
    return payerClassification;
  }

  public TransactionsReportedClassification getTransactionsReportedClassification() {
    return transactionsReportedClassification;
  }

  public Optional<String> getPseName() {
    return Optional.ofNullable(pseName);
  }

  public Optional<String> getPsePhoneNumber() {
    return Optional.ofNullable(psePhoneNumber);
  }

  public Optional<String> getAccountNumber() {
    return Optional.ofNullable(accountNumber);
  }

  public Double getAggregateGrossAmount() {
    return aggregateGrossAmount;
  }

  public Double getAggregateGrossAmountCardNotPresent() {
    return aggregateGrossAmountCardNotPresent;
  }

  public Optional<String> getMerchantCategoryCode() {
    return Optional.ofNullable(merchantCategoryCode);
  }

  public int getNumberOfPaymentTransactions() {
    return numberOfPaymentTransactions;
  }

  public Double getFederalIncomeTaxWithheld() {
    return federalIncomeTaxWithheld;
  }

  public GrossAmountsByMonth getGrossAmountsByMonth() {
    return grossAmountsByMonth;
  }

  public List<StateTaxInfo> getStateTaxInfo() {
    return stateTaxInfo == null ? Collections.emptyList() : stateTaxInfo;
  }
}
