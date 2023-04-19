package com.withabound.resources;

import com.withabound.AboundConfig;
import com.withabound.models.documents.Document;
import com.withabound.models.documents.DocumentParams;
import com.withabound.models.documents.IDocumentRequest;
import com.withabound.resources.base.AboundBulkResponse;
import com.withabound.resources.base.AboundResponse;
import com.withabound.resources.base.AboundUserScopedResource;
import com.withabound.resources.base.EmptyJsonObject;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;

/** See https://docs.withabound.com/reference/documents */
public class Documents extends AboundUserScopedResource<IDocumentRequest, Document> {
  public Documents(final AboundConfig aboundConfig, final OkHttpClient httpClient) {
    super(aboundConfig, httpClient, Document.class);
  }

  @Override
  protected String getPath() {
    return "/documents";
  }

  public AboundBulkResponse<Document> create(
      final String userId, final List<IDocumentRequest> toCreate) throws IOException {
    final Map<String, List<IDocumentRequest>> requestBody =
        Collections.singletonMap("documents", toCreate);

    return super.bulkCreateForUser(userId, requestBody);
  }

  @Override
  public AboundBulkResponse<Document> list(final String userId) throws IOException {
    return super.listForUser(userId);
  }

  public AboundBulkResponse<Document> list(final String userId, final DocumentParams params)
      throws IOException {
    return super.listForUser(userId, params);
  }

  public AboundResponse<Document> retrieve(final String userId, final String documentId)
      throws IOException {
    return super.retrieveForUser(userId, documentId);
  }

  public AboundResponse<EmptyJsonObject> delete(final String userId, final String documentId)
      throws IOException {
    return super.deleteForUser(userId, documentId);
  }
}
