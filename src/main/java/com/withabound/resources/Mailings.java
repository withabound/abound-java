package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.mailings.Mailing;
import com.withabound.models.mailings.MailingParams;
import com.withabound.models.mailings.MailingRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundDocumentScopedResource;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.EmptyJsonObject;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import okhttp3.OkHttpClient;

public class Mailings extends AboundDocumentScopedResource<MailingRequest, Mailing> {
  public Mailings(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, Mailing.class);
  }

  @Override
  protected String getPath() {
    return "/mailings";
  }

  public AboundBulkResponse<Mailing> list(final String userId, final String documentId)
      throws IOException {
    return super.listForDocument(userId, documentId);
  }

  public AboundBulkResponse<Mailing> list(
      final String userId, final String documentId, final MailingParams params) throws IOException {
    return super.listForDocument(userId, documentId, params);
  }

  public AboundResponse<Mailing> create(final String userId, final String documentId)
      throws IOException {

    return super.createForDocument(userId, documentId, Collections.emptyMap());
  }

  public AboundResponse<Mailing> create(
      final String userId, final String documentId, final MailingRequest toCreate)
      throws IOException {

    final Map<String, MailingRequest> requestPayload =
        Collections.singletonMap("mailing", toCreate);

    return super.createForDocument(userId, documentId, requestPayload);
  }

  public AboundResponse<Mailing> retrieve(
      final String userId, final String documentId, final String mailingId) throws IOException {
    return super.retrieveForDocument(userId, documentId, mailingId);
  }

  public AboundResponse<EmptyJsonObject> delete(
      final String userId, final String documentId, final String mailingId) throws IOException {
    return super.deleteForDocument(userId, documentId, mailingId);
  }
}
