package com.withabound.models.documents.ten99_k;

import com.withabound.models.documents.DocumentRequest;
import com.withabound.models.documents.DocumentType;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Form1099KDocumentRequest extends DocumentRequest {
  /** The payer filing or issuing this form. */
  private String payerId;

  /** The tax year associated with this form. */
  private Integer year;

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
  private Double numberOfPaymentTransactions;

  /**
   * The federal income tax withheld. Generally, a payer must backup withhold if you did not furnish
   * your TIN or you did not furnish the correct TIN to the payer.
   */
  private Double federalIncomeTaxWithheld;

  private GrossAmountsByMonth grossAmountsByMonth;

  @Override
  public DocumentType getType() {
    return DocumentType.TEN99K;
  }

  public String getPayerId() {
    return payerId;
  }

  public Integer getYear() {
    return year;
  }

  public PayerClassification getPayerClassification() {
    return payerClassification;
  }

  public TransactionsReportedClassification getTransactionsReportedClassification() {
    return transactionsReportedClassification;
  }

  public String getPseName() {
    return pseName;
  }

  public String getPsePhoneNumber() {
    return psePhoneNumber;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public Double getAggregateGrossAmount() {
    return aggregateGrossAmount;
  }

  public Double getAggregateGrossAmountCardNotPresent() {
    return aggregateGrossAmountCardNotPresent;
  }

  public String getMerchantCategoryCode() {
    return merchantCategoryCode;
  }

  public Double getNumberOfPaymentTransactions() {
    return numberOfPaymentTransactions;
  }

  public Double getFederalIncomeTaxWithheld() {
    return federalIncomeTaxWithheld;
  }

  public GrossAmountsByMonth getGrossAmountsByMonth() {
    return grossAmountsByMonth;
  }
}
