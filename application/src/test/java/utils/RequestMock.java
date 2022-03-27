package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import spark.Request;

public class RequestMock extends Request {

  private int status;
  private String body;
  private String method;
  private String uri;
  private Map<String, Object> attribute;
  private Map<String, String> queryParams;
  private final Map<String, String> param;

  public RequestMock() {
    this.param = new HashMap<>();
    this.attribute = new HashMap<>();
    this.queryParams = new HashMap<>();
  }

  public int status() {
    return status;
  }

  public String body() {
    return body;
  }

  public void body(String body) {
    this.body = body;
  }

  public Map<String, Object> attribute() {
    return attribute;
  }

  public Set<String> queryParams() {
    return queryParams.keySet();
  }

  public void queryParams(Map<String, String> queryParams) {
    this.queryParams = queryParams;
  }

  public void setRequestMethod(String method) {
    this.method = method;
  }

  @Override
  public String requestMethod() {
    return this.method;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @Override
  public String uri() {
    return this.uri;
  }

  @Override
  public String params(String key) {
    return this.param.get(key);
  }

  public void addParam(String key, String value) {
    this.param.put(key, value);
  }

  public void status(int status) {
    this.status = status;
  }

  public void attribute(String attribute, Object value) {
    this.attribute = Map.of(attribute, value);
  }

  public Map<String, Object> attribute(String attribute) {
    return this.attribute != null ? this.attribute : Map.of();
  }
}
