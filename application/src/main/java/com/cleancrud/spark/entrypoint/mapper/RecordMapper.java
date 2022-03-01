package com.cleancrud.spark.entrypoint.mapper;

import com.cleancrud.spark.entrypoint.dto.RecordDto;
import entity.Record;
import javax.inject.Singleton;

@Singleton
public class RecordMapper {

  private RecordMapper() {
  }

  public static RecordDto entityToDto(Record record) {
    return new RecordDto(
        record.getId(),
        record.getData()
    );
  }

  public static Record dtoToEntity(RecordDto recordDto) {
    return Record.builder()
        .id(recordDto.getId())
        .data(recordDto.getData())
        .build();
  }

}
