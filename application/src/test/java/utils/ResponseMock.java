package utils;

import spark.Response;

public class ResponseMock extends Response {

  private int status;
  private String type;

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

  @Override
  public void type(String type) {
    this.type = type;
  }

  @Override
  public String type() {
    return this.type;
  }
}
