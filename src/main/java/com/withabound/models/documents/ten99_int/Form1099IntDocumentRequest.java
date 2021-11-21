package com.withabound.models.documents.ten99_int;

import com.withabound.models.documents.DocumentRequest;
import com.withabound.models.documents.DocumentType;
import com.withabound.models.documents.StateTaxInfo;
import java.util.List;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class Form1099IntDocumentRequest extends DocumentRequest {
  /** The payer filing or issuing this form. */
  private String payerId;

  /** The tax year associated with this form. */
  private Integer year;

  /** Satisfying a requirement to report with respect to a U.S. account for chapter 4 purposes. */
  private Boolean hasFatcaFilingRequirement;

  /**
   * The IRS “encourages” a payer to designate an account number for all Forms 1099-INT filed. This
   * field is required if payer has multiple accounts for a recipient for whom it is filing more
   * than one Form 1099-INT. This field is also required if {@link
   * Form1099IntDocumentRequest#hasFatcaFilingRequirement} is <code>true</code>>.
   */
  private String accountNumber;

  private String payersRoutingNumber;

  private Double interestIncome;

  /**
   * The interest or principal forfeited because of early withdrawal of time savings. You may deduct
   * this amount to determine your adjusted gross income on your income tax return.
   */
  private Double earlyWithdrawalPenalty;

  /**
   * Shows interest on U.S. Savings Bonds, Treasury bills, Treasury bonds, and Treasury notes. This
   * may or may not all be taxable. This interest is exempt from state and local income taxes. This
   * interest is not included in {@link Form1099IntDocumentRequest#interestIncome}.
   */
  private Double usSavingsBondsInterest;

  /**
   * The federal income tax withheld. A payer must backup withhold on certain payments if recipient
   * did not provide a TIN to the payer.
   */
  private Double federalIncomeTaxWithheld;

  /**
   * Any amount is your share of investment expenses of a singleclass REMIC. This amount is included
   * in {@link Form1099IntDocumentRequest#interestIncome}. This amount is not deductible.
   */
  private Double investmentExpenses;

  /** The foreign tax paid. */
  private Double foreignTaxPaid;

  /** The country or U.S. possession to which the foreign tax was paid. */
  private String foreignTaxPaidCountry;

  /**
   * The tax-exempt interest paid to you during the calendar year by the payer. This amount may be
   * subject to backup withholding. See {@link Form1099IntDocumentRequest#federalIncomeTaxWithheld}.
   */
  private Double taxExemptInterest;

  /**
   * The tax-exempt interest subject to the alternative minimum tax. This amount is included in
   * {@link Form1099IntDocumentRequest#taxExemptInterest}.
   */
  private Double specifiedPrivateActivityBondInterest;

  /**
   * For a taxable or tax-exempt covered security, if you made an election under section 1278(b) to
   * include market discount in income as it accrues and you notified your payer of the election in
   * writing in accordance with Regulations section 1.6045-1(n)(5), shows the market discount that
   * accrued on the debt instrument during the year while held by you, unless it was reported on
   * Form 1099-OID.
   */
  private Double marketDiscount;

  /**
   * For a taxable covered security (other than a U.S. Treasury obligation), shows the amount of
   * premium amortization allocable to the interest payment(s), unless you notified the payer in
   * writing in accordance with Regulations section 1.6045-1(n)(5) that you did not want to amortize
   * bond premium under section 171.
   */
  private Double bondPremium;

  /**
   * For a U.S. Treasury obligation that is a covered security, shows the amount of premium
   * amortization allocable to the interest payment(s), unless you notified the payer in writing in
   * accordance with Regulations section 1.6045-1(n)(5) that you did not want to amortize bond
   * premium under section 171.
   */
  private Double bondPremiumTreasury;

  /**
   * For a tax-exempt covered security, shows the amount of premium amortization allocatable to the
   * interest payment(s).
   */
  private Double bondPremiumTaxExemptBond;

  /** Up to two (2) state tax information objects. */
  private List<StateTaxInfo> stateTaxInfo;

  @Override
  public DocumentType getType() {
    return DocumentType.TEN99INT;
  }

  public String getPayerId() {
    return payerId;
  }

  public Integer getYear() {
    return year;
  }

  public Boolean getHasFatcaFilingRequirement() {
    return hasFatcaFilingRequirement;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getPayersRoutingNumber() {
    return payersRoutingNumber;
  }

  public Double getInterestIncome() {
    return interestIncome;
  }

  public Double getEarlyWithdrawalPenalty() {
    return earlyWithdrawalPenalty;
  }

  public Double getUsSavingsBondsInterest() {
    return usSavingsBondsInterest;
  }

  public Double getFederalIncomeTaxWithheld() {
    return federalIncomeTaxWithheld;
  }

  public Double getInvestmentExpenses() {
    return investmentExpenses;
  }

  public Double getForeignTaxPaid() {
    return foreignTaxPaid;
  }

  public String getForeignTaxPaidCountry() {
    return foreignTaxPaidCountry;
  }

  public Double getTaxExemptInterest() {
    return taxExemptInterest;
  }

  public Double getSpecifiedPrivateActivityBondInterest() {
    return specifiedPrivateActivityBondInterest;
  }

  public Double getMarketDiscount() {
    return marketDiscount;
  }

  public Double getBondPremium() {
    return bondPremium;
  }

  public Double getBondPremiumTreasury() {
    return bondPremiumTreasury;
  }

  public Double getBondPremiumTaxExemptBond() {
    return bondPremiumTaxExemptBond;
  }

  public List<StateTaxInfo> getStateTaxInfo() {
    return stateTaxInfo;
  }
}
