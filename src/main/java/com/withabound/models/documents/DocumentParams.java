package com.withabound.models.documents;

import com.withabound.resources.base.AboundQueryParameters;
import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class DocumentParams extends AboundQueryParameters {
  private String page;

  private String year;

  @Override
  public Map<String, Object> asMap() {
    final Map<String, Object> queryParams = new HashMap<>();

    queryParams.put("page", page);
    queryParams.put("year", year);

    return queryParams;
  }
}
