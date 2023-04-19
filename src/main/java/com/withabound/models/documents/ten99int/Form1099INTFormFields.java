package com.withabound.models.documents.ten99int;

import com.withabound.models.documents.StateTaxInfo;
import java.util.List;
import java.util.Optional;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
public class Form1099INTFormFields {
  /** If `true`, the corrected checkbox will be marked on the document. */
  private Boolean isCorrected;

  /** Satisfying a requirement to report with respect to a U.S. account for chapter 4 purposes. */
  private Boolean hasFatcaFilingRequirement;

  /**
   * The IRS “encourages” a payer to designate an account number for all Forms 1099-INT filed. This
   * field is required if payer has multiple accounts for a recipient for whom it is filing more
   * than one Form 1099-INT. This field is also required if {@link
   * Form1099INTDocumentRequest#hasFatcaFilingRequirement} is <code>true</code>>.
   */
  private String accountNumber;

  /**
   * A routing and transit number (RTN) is a unique nine-digit number used to identify a bank for
   * purposes of directing financial flows. This is essentially bank's bank account – their account
   * with the Federal Reserve.
   */
  private String payersRoutingNumber;

  /**
   * The taxable interest paid to you during the calendar year by the payer. This does not include
   * interest for {@link Form1099INTDocumentRequest#usSavingsBondsInterest}. May also show the total
   * amount of the credits from clean renewable energy bonds, new clean renewable energy bonds,
   * qualified energy conservation bonds, qualified zone academy bonds, qualified school
   * construction bonds, and build America bonds that must be included in your interest income.
   */
  private Double interestIncome;

  /**
   * The interest or principal forfeited because of early withdrawal of time savings. You may deduct
   * this amount to determine your adjusted gross income on your income tax return.
   */
  private Double earlyWithdrawalPenalty;

  /**
   * Shows interest on U.S. Savings Bonds, Treasury bills, Treasury bonds, and Treasury notes. This
   * may or may not all be taxable. This interest is exempt from state and local income taxes. This
   * interest is not included in {@link Form1099INTDocumentRequest#interestIncome}.
   */
  private Double usSavingsBondsInterest;

  /**
   * The federal income tax withheld. A payer must backup withhold on certain payments if recipient
   * did not provide a TIN to the payer.
   */
  private Double federalIncomeTaxWithheld;

  /**
   * Any amount is your share of investment expenses of a singleclass REMIC. This amount is included
   * in {@link Form1099INTDocumentRequest#interestIncome}. This amount is not deductible.
   */
  private Double investmentExpenses;

  /** The foreign tax paid. */
  private Double foreignTaxPaid;

  /** The country or U.S. possession to which the foreign tax was paid. */
  private String foreignTaxPaidCountry;

  /**
   * The tax-exempt interest paid to you during the calendar year by the payer. This amount may be
   * subject to backup withholding. See {@link Form1099INTDocumentRequest#federalIncomeTaxWithheld}.
   */
  private Double taxExemptInterest;

  /**
   * The tax-exempt interest subject to the alternative minimum tax. This amount is included in
   * {@link Form1099INTDocumentRequest#taxExemptInterest}.
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

  public Optional<Boolean> getIsCorrected() {
    return Optional.ofNullable(isCorrected);
  }

  public Boolean getHasFatcaFilingRequirement() {
    return hasFatcaFilingRequirement;
  }

  public Optional<String> getAccountNumber() {
    return Optional.ofNullable(accountNumber);
  }

  public Optional<String> getPayersRoutingNumber() {
    return Optional.ofNullable(payersRoutingNumber);
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

  public Optional<String> getForeignTaxPaidCountry() {
    return Optional.ofNullable(foreignTaxPaidCountry);
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
