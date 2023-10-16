package api.request;

import api.core.BaseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import constants.APIKeys;
import constants.Constant;
import model.MProduct;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ApiProducts extends BaseAPI {

  public ApiProducts() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    super();
    this.path = Constant.API_PRODUCTS;
  }

  public JsonNode get()
      throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException,
          URISyntaxException {

    this.uriBuilder.setPath(BaseAPI.url + this.path);
    withDefaultHeader();
    return doGet();
  }

  public JsonNode delete(String id)
      throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyStoreException,
          KeyManagementException {
    withDefaultHeader();
    this.uriBuilder.setPath(BaseAPI.url + this.path + "/" + id);
    return doDelete();
  }

  public JsonNode post()
      throws IOException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException,
          KeyManagementException {

    this.uriBuilder.setPath(BaseAPI.url + this.path);
    withDefaultHeader();
    return doPost();
  }

  public List<MProduct> getProducts() throws IOException {
    return new ArrayList<>() {
      {
        for (JsonNode node : responsePayload)
          add(objectMapper.readValue(node.toString(), MProduct.class));
      }
    };
  }

  public List<MProduct> getProductsByCategory(String categoryId) throws IOException {
    return new ArrayList<>() {
      {
        for (JsonNode node : responsePayload)
          if (node.get(APIKeys.CATEGORY_ID).asText().equals(categoryId)) {
            add(objectMapper.readValue(node.toString(), MProduct.class));
          }
      }
    };
  }
}
