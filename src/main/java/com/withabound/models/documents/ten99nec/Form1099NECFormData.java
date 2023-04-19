package com.withabound.models.documents.ten99nec;

import com.withabound.models.documents.Form1099Payer;
import com.withabound.models.documents.Form1099User;

public class Form1099NECFormData extends Form1099NECFormFields {
  protected Form1099NECFormData(Form1099NECFormFieldsBuilder<?, ?> formFields) {
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
