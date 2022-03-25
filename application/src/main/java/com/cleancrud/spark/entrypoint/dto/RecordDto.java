package com.cleancrud.spark.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class RecordDto {

  Long id;
  String data;

  @JsonCreator
  public RecordDto(
      @JsonProperty("id") final Long id,
      @JsonProperty("data") final String data) {
    this.id = id;
    this.data = data;
  }
}
