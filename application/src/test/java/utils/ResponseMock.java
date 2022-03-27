package utils;

import java.util.HashMap;
import java.util.Map;
import spark.Response;

public class ResponseMock extends Response {

  private int status;
  private String type;
  private Map<String, String> header = new HashMap<>();

  public ResponseMock() {
  }

  public int status() {
    return status;
  }

  @Override
  public void status(int status) {
    this.status = status;
  }

  public void body(String body) {
    super.body(body);
  }

  public String body() {
    return super.body();
  }

  public Map<String, String> header() {
    return header;
  }

  public void header(Map<String, String> header) {
    this.header = header;
  }

  @Override
  public void type(String type) {
    this.type = type;
  }

  @Override
  public String type() {
    return this.type = type;
  }
}
