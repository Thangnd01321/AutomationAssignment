package api.core;

import java.util.HashMap;
import java.util.Map;

public class RequestParams {
  private Map<String, String> requestParams;

  public RequestParams() {
    this.requestParams = new HashMap<>();
  }

  public RequestParams clear() {
    this.requestParams.clear();
    return this;
  }

  public RequestParams putRequestParam(String key, String value) {
    this.requestParams.put(key, value);
    return this;
  }

  public Map<String, String> getRequestParams() {
    return this.requestParams;
  }

  public RequestParams setRequestParams(Map<String, String> params) {
    this.requestParams = params;
    return this;
  }
}
