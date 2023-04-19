package com.withabound.models.documents.w9;

public class FormW9FormData extends FormW9FormFields {
  protected FormW9FormData(FormW9FormFieldsBuilder<?, ?> formFields) {
    super(formFields);
  }

  private FormW9User user;
  private FormW9Payer payer;

  public FormW9User getUser() {
    return user;
  }

  public FormW9Payer getPayer() {
    return payer;
  }
}
