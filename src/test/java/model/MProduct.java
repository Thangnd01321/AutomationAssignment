package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import constants.APIKeys;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MProduct {
  @JsonProperty(APIKeys.ID)
  private String id;

  @JsonProperty(APIKeys.NAME)
  private String name;

  @JsonProperty(APIKeys.DESCRIPTION)
  private String description;

  @JsonProperty(APIKeys.PRICE)
  private String price;
  @JsonProperty(APIKeys.STOCK)
  private String stock;
  @JsonProperty(APIKeys.CATEGORY_ID)
  private String categoryId;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getStock() {
    return stock;
  }

  public void setStock(String stock) {
    this.stock = stock;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
