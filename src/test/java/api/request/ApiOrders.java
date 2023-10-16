package api.request;

import api.core.BaseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import constants.Constant;
import model.MOrder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ApiOrders extends BaseAPI {

  public ApiOrders() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    super();
    this.path = Constant.API_ORDERS;
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

  public JsonNode put(String id)
      throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyStoreException,
          KeyManagementException {
    withDefaultHeader();
    this.uriBuilder.setPath(BaseAPI.url + this.path + "/" + id);
    return doPut();
  }

  public JsonNode post()
      throws IOException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException,
          KeyManagementException {

    this.uriBuilder.setPath(BaseAPI.url + this.path);
    withDefaultHeader();
    return doPost();
  }

  public List<MOrder> getOrders() throws IOException {
    return new ArrayList<>() {
      {
        for (JsonNode node : responsePayload)
          add(objectMapper.readValue(node.toString(), MOrder.class));
      }
    };
  }

  public MOrder getOrder() throws IOException {
    return objectMapper.readValue(responsePayload.toString(), MOrder.class);
  }
}
