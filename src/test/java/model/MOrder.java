package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import constants.APIKeys;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MOrder {
  @JsonProperty(APIKeys.ID)
  private String id;

  @JsonProperty(APIKeys.USER_ID)
  private String userId;

  @JsonProperty(APIKeys.STATUS)
  private String status;

  @JsonProperty(APIKeys.ITEMS)
  private List<MItem> items;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<MItem> getItems() {
    return items;
  }

  public void setItems(List<MItem> items) {
    this.items = items;
  }
}
