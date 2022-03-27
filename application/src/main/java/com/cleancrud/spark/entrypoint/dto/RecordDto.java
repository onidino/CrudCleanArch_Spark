package com.cleancrud.spark.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

@Value
@Data
public class RecordDto {

  Long id;
  String recordData;

  @JsonCreator
  public RecordDto(
      @JsonProperty("id") final Long id,
      @JsonProperty("record_data") final String recordData) {
    this.id = id;
    this.recordData = recordData;
  }
}
