package com.withabound.models.mailings;

import lombok.Getter;

@Getter
public class Mailing {
  private String mailingId;

  private MailingAddress to;

  private MailingAddress from;

  private String status;

  private Long createdTimestamp;

  public String getMailingId() {
    return mailingId;
  }

  public MailingAddress getTo() {
    return to;
  }

  public MailingAddress getFrom() {
    return from;
  }

  public String getStatus() {
    return status;
  }

  public Long getCreatedTimestamp() {
    return createdTimestamp;
  }
}
