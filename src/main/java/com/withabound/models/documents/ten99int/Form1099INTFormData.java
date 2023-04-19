package com.withabound.models.documents.ten99int;

import com.withabound.models.documents.Form1099Payer;
import com.withabound.models.documents.Form1099User;

public class Form1099INTFormData extends Form1099INTFormFields {
  protected Form1099INTFormData(Form1099INTFormFieldsBuilder<?, ?> formFields) {
    super(formFields);
  }

  private Form1099User user;
  private Form1099Payer payer;

  public Form1099User getUser() {
    return user;
  }

  public Form1099Payer getPayer() {
    return payer;
  }
}
