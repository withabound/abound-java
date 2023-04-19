package com.withabound.models.documents.ten99k;

import com.withabound.models.documents.Form1099Payer;
import com.withabound.models.documents.Form1099User;

public class Form1099KFormData extends Form1099KFormFields {
  protected Form1099KFormData(Form1099KFormFieldsBuilder<?, ?> formFields) {
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
