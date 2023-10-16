package api.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.*;
import utils.logging.Log;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class BaseAPI {
  protected static final ObjectMapper objectMapper = new ObjectMapper();
  public static String url;
  protected String path;
  protected URIBuilder uriBuilder = new URIBuilder();
  protected RequestParams requestParams;
  protected ResponseEntity<String> response;
  protected HttpStatus responseStatus;
  protected HttpHeaders requestHeader;
  protected JsonNode responsePayload;
  private ObjectNode requestBody;
  private Boolean onlyAcceptStatusOk;
  private Boolean isAllowApiError = false;

  public BaseAPI() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    requestParams = new RequestParams();
    requestBody = objectMapper.createObjectNode();
  }

  public Boolean getAllowApiError() {
    return isAllowApiError;
  }

  public void setAllowApiError(Boolean allowApiError) {
    isAllowApiError = allowApiError;
  }

  protected JsonNode doGet()
      throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
          URISyntaxException, IOException {

    HttpEntity<String> request = new HttpEntity<String>(null, this.requestHeader);
    this.uriBuilder.clearParameters();

    // Add params
    this.requestParams
        .getRequestParams()
        .forEach((key, value) -> this.uriBuilder.addParameter(key, value));

    URI uri = this.uriBuilder.build();

    Log.details("\nSend Get to API url: " + uri);
    Log.details("Request header: " + request.getHeaders());

    this.exchange(uri, request, HttpMethod.GET);

    Log.details(
        "Response Http Status: "
            + responseStatus
            + "\nResponse payload: "
            + responsePayload.toString());
    if (!isAllowApiError) {
      throwError();
    }

    return responsePayload;
  }

  protected JsonNode doDelete()
      throws IOException, URISyntaxException, NoSuchAlgorithmException, KeyStoreException,
          KeyManagementException {

    HttpEntity<String> request = new HttpEntity<String>(null, this.requestHeader);
    // Add params
    this.requestParams
        .getRequestParams()
        .forEach((key, value) -> this.uriBuilder.addParameter(key, value));

    URI uri = this.uriBuilder.build();

    Log.details("\nSend Delete to API url: " + uri);
    Log.details("Request header: " + request.getHeaders());

    this.exchange(uri, request, HttpMethod.DELETE);

    Log.details(
        "Response Http Status: "
            + responseStatus
            + "\nResponse payload: "
            + responsePayload.toString());
    if (!isAllowApiError) {
      throwError();
    }

    return responsePayload;
  }

  protected JsonNode doPost()
      throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
          URISyntaxException {
    HttpEntity<String> request = new HttpEntity<String>(requestBody.toString(), this.requestHeader);
    this.uriBuilder.clearParameters();

    // Add params
    this.requestParams
        .getRequestParams()
        .forEach((key, value) -> this.uriBuilder.addParameter(key, value));

    URI uri = this.uriBuilder.build();

    Log.details("\nSend Post to API url: " + uri);
    Log.details("Request header: " + request.getHeaders());
    Log.details("Request body: " + request.getBody());

    this.exchange(uri, request, HttpMethod.POST);

    Log.details(
        "Response Http Status: "
            + responseStatus
            + "\nResponse payload: "
            + responsePayload.toString());
    if (!isAllowApiError) {
      throwError();
    }

    return responsePayload;
  }

  protected JsonNode doPut()
      throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException,
          URISyntaxException {

    HttpEntity<String> request = new HttpEntity<String>(requestBody.toString(), this.requestHeader);

    // Add params
    this.requestParams
        .getRequestParams()
        .forEach((key, value) -> this.uriBuilder.addParameter(key, value));

    URI uri = this.uriBuilder.build();

    Log.details("\nSend Put to API url: " + uri);
    Log.details("Request header: " + request.getHeaders());
    Log.details("Request body: " + request.getBody());

    this.exchange(uri, request, HttpMethod.PUT);

    Log.details(
        "Response Http Status: "
            + responseStatus
            + "\nResponse payload: "
            + responsePayload.toString());

    if (!isAllowApiError) {
      throwError();
    }

    return responsePayload;
  }

  public JsonNode getRequestBody() throws IOException {
    return this.requestBody;
  }

  public void setRequestBody(ObjectNode node) {
    this.requestBody = node;
  }

  public ObjectNode clearRequestBody() {
    return this.requestBody.removeAll();
  }

  // --------------------****************
  public HttpComponentsClientHttpRequestFactory getRequestFactory()
      throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
    SSLContext sslContext =
        org.apache.http.ssl.SSLContexts.custom()
            .loadTrustMaterial(null, acceptingTrustStrategy)
            .build();

    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
    CloseableHttpClient httpClient =
        HttpClients.custom()
            .setSSLSocketFactory(csf)
            .setDefaultRequestConfig(
                RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
            .build();

    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();

    requestFactory.setHttpClient(httpClient);
    requestFactory.setConnectTimeout(10000);
    requestFactory.setReadTimeout(60000);
    requestFactory.setConnectionRequestTimeout(10000);
    return requestFactory;
  }

  public JsonNode getResponsePayload() {
    return this.responsePayload;
  }

  public void setResponsePayload(String raw) throws IOException {
    this.responsePayload = objectMapper.readTree(raw);
  }

  public int getResponseStatusCode() {
    return response.getStatusCodeValue();
  }

  public HttpStatus getResponseHttpStatus() {
    return this.responseStatus;
  }

  public void setResponseHttpStatus(HttpStatus status) {
    this.responseStatus = status;
  }

  public HttpHeaders getResponseHeader() {
    return response.getHeaders();
  }

  public void setPath(String path) {
    this.path = path;
  }

  public boolean isServerError() {
    HttpStatus status = getResponseHttpStatus();
    return (status.is5xxServerError());
  }

  public boolean isClientError() {
    HttpStatus status = getResponseHttpStatus();
    return (status.is4xxClientError());
  }

  public void throwError() throws IOException {
    HttpStatus status = getResponseHttpStatus();
    if (status.isError()) {
      switch (status.series()) {
        case CLIENT_ERROR:
          throw new HttpClientErrorException(status, getResponsePayload().toString());
        case SERVER_ERROR:
          throw new HttpServerErrorException(status, getResponsePayload().toString());
      }
    }
  }

  public RequestParams getRequestParams() {
    return this.requestParams;
  }

  public void setRequestParams(RequestParams params) {
    this.requestParams = params;
  }

  public RequestParams clearRequestParams() {
    return this.requestParams.clear();
  }

  public RequestParams putRequestParam(String key, String value) {
    return this.requestParams.putRequestParam(key, value);
  }

  /**
   * @param uri
   * @param request
   * @param method
   * @return JSonNode - Payload
   * @throws IOException
   * @throws KeyStoreException
   * @throws NoSuchAlgorithmException
   * @throws KeyManagementException
   */
  public JsonNode exchange(URI uri, HttpEntity<String> request, HttpMethod method)
      throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    try {
      RestTemplate restTemplate = new RestTemplate(getRequestFactory());
      response = restTemplate.exchange(uri, method, request, String.class);
      setResponseHttpStatus(response.getStatusCode());
      setResponsePayload(response.getBody());
    } catch (HttpStatusCodeException e) {
      setResponseHttpStatus(e.getStatusCode());
      setResponsePayload(e.getResponseBodyAsString());
    } catch (ResourceAccessException ae) {
      throw ae;
    }
    return this.responsePayload;
  }

  public Boolean isOnlyAcceptStatusOk() {
    return onlyAcceptStatusOk;
  }

  public void setOnlyAcceptStatusOk(Boolean onlyAcceptStatusOk) {
    this.onlyAcceptStatusOk = onlyAcceptStatusOk;
  }

  public BaseAPI clear() {
    this.requestParams.clear();
    this.clearRequestBody();
    this.responsePayload = objectMapper.createObjectNode();
    return this;
  }

  public void withDefaultHeader() {
    requestHeader = new HttpHeaders();
    requestHeader.setContentType(MediaType.APPLICATION_JSON);
  }
}
