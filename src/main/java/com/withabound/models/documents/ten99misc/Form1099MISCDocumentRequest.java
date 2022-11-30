// package com.withabound.models.documents.ten99misc;

// import com.withabound.models.documents.DocumentRequest;
// import com.withabound.models.documents.DocumentType;
// import com.withabound.models.documents.StateTaxInfoWithIncome;
// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;
// import lombok.Builder;
// import lombok.Setter;

// @Builder
// @Setter
// public class Form1099MISCDocumentRequest extends DocumentRequest {
//   /** The payer filing or issuing this form. */
//   private String payerId;

//   /** The tax year associated with this form. */
//   private Integer year;

//   /**
//    * Required if you have multiple accounts for a recipient for whom you are filing more than one
//    * Form 1099-MISC. Required if the fatca is true.
//    */
//   private String accountNumber;

//   /** Satisfying a requirement to report with respect to a U.S. account for chapter 4 purposes.
// */
//   private Boolean hasFatcaFilingRequirement;

//   /**
//    * Report rents from real estate on Schedule E (Form 1040). However, report rents on Schedule C
//    * (Form 1040) if you provided significant services to the tenant, sold real estate as a
// business,
//    * or rented personal property as a business.
//    */
//   private Double rents;

//   /**
//    * Report royalties from oil, gas, or mineral properties; copyrights; and patents on Schedule E
//    * (Form 1040).
//    */
//   private Double royalties;

//   /**
//    * The amount shown may be payments received as the beneficiary of a deceased employee, prizes,
//    * awards, taxable damages, Indian gaming profits, or other taxable income.
//    */
//   private Double otherIncome;

//   /**
//    * The backup withholding or withholding on Indian gaming profits. Generally, a payer must
// backup
//    * withhold if recipient did not furnish a TIN.
//    */
//   private Double federalIncomeTaxWithheld;

//   /**
//    * The amount paid to a fishing boat crew member who is considered by the operator to be
//    * self-employed.
//    */
//   private Double fishingBoatProceeds;

//   /** The medical and health care payments for individuals. */
//   private Double medicalPayments;

//   /**
//    * If true, consumer products totaling $5,000 or more were sold for resale, on a buy-sell, a
//    * deposit-commission, or other basis.
//    */
//   private Boolean hasDirectSalesOver5000;

//   /**
//    * The substitute payments in lieu of dividends or tax-exempt interest received by your broker
// on
//    * your behalf as a result of a loan of your securities.
//    */
//   private Double substitutePayments;

//   /**
//    * The crop insurance proceeds of $600 or more paid to farmers by insurance companies unless
// the
//    * farmer has informed the insurance company that expenses have been capitalized under section
//    * 278, 263A, or 447.
//    */
//   private Double cropInsuranceProceeds;

//   /** The gross proceeds paid to an attorney in connection with legal services. */
//   private Double grossProceedsAttorney;

//   /**
//    * The amount paid for the purchase of fish for resale from any person engaged in the trade or
//    * business of catching fish.
//    */
//   private Double fishPurchasedForResale;

//   /**
//    * The current year deferrals as a nonemployee under a nonqualified deferred compensation
// (NQDC)
//    * plan that is subject to the requirements of section 409A plus any earnings on current and
// prior
//    * year deferrals.
//    */
//   private Double section409ADeferrals;

//   /** The total compensation of excess golden parachute payments subject to a 20% excise tax. */
//   private Double excessGoldenParachutePayments;

//   /**
//    * The income as a non-employee under an NQDC plan that does not meet the requirements of
// section
//    * 409A. Any amount included in section409A that is currently taxable should also be included.
//    */
//   private Double nqdc;

//   /** Up to two (2) state tax information objects. */
//   private List<StateTaxInfoWithIncome> stateTaxInfo;

//   @Override
//   public DocumentType getType() {
//     return DocumentType.TEN99MISC;
//   }

//   public String getPayerId() {
//     return payerId;
//   }

//   public Integer getYear() {
//     return year;
//   }

//   public Optional<String> getAccountNumber() {
//     return Optional.ofNullable(accountNumber);
//   }

//   public Boolean getHasFatcaFilingRequirement() {
//     return hasFatcaFilingRequirement;
//   }

//   public Double getRents() {
//     return rents;
//   }

//   public Double getRoyalties() {
//     return royalties;
//   }

//   public Double getOtherIncome() {
//     return otherIncome;
//   }

//   public Double getFederalIncomeTaxWithheld() {
//     return federalIncomeTaxWithheld;
//   }

//   public Double getFishingBoatProceeds() {
//     return fishingBoatProceeds;
//   }

//   public Boolean getHasDirectSalesOver5000() {
//     return hasDirectSalesOver5000;
//   }

//   public Double getSubstitutePayments() {
//     return substitutePayments;
//   }

//   public Double getCropInsuranceProceeds() {
//     return cropInsuranceProceeds;
//   }

//   public Double getGrossProceedsAttorney() {
//     return grossProceedsAttorney;
//   }

//   public Double getFishPurchasedForResale() {
//     return fishPurchasedForResale;
//   }

//   public Double getSection409ADeferrals() {
//     return section409ADeferrals;
//   }

//   public Double getExcessGoldenParachutePayments() {
//     return excessGoldenParachutePayments;
//   }

//   public Double getNqdc() {
//     return nqdc;
//   }

//   public List<StateTaxInfoWithIncome> getStateTaxInfo() {
//     return stateTaxInfo;
//   }
// }
