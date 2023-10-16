package api.request;

import api.core.BaseAPI;
import com.fasterxml.jackson.databind.JsonNode;
import constants.Constant;
import model.MUser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class ApiUsers extends BaseAPI {

  public ApiUsers() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    super();
    this.path = Constant.API_USERS;
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

  public List<MUser> getUsers() throws IOException {
    return new ArrayList<>() {
      {
        for (JsonNode node : responsePayload)
          add(objectMapper.readValue(node.toString(), MUser.class));
      }
    };
  }

  public MUser getUser() throws IOException {
    return objectMapper.readValue(responsePayload.toString(), MUser.class);
  }
}
