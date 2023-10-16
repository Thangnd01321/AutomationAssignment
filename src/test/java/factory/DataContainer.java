package factory;

import api.core.BaseAPI;
import utils.enums.HttpRequest;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class DataContainer {
  public String site;
  public BaseAPI api;
  public HttpRequest request;

  public DataContainer()
      throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    api = new BaseAPI();
    // Default to GET
    request = HttpRequest.GET;
  }
}
