package com.cleancrud.spark.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseException {

  String exception;
  String message;

  @JsonCreator
  public ResponseException(
      @JsonProperty("exception") String exception,
      @JsonProperty("message") String message) {
    this.exception = exception;
    this.message = message;
  }
}
