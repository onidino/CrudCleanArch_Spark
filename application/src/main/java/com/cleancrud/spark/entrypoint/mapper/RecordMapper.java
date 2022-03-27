package com.cleancrud.spark.entrypoint.mapper;

import com.cleancrud.spark.entrypoint.dto.RecordDto;
import entity.Record;
import javax.inject.Singleton;

@Singleton
public class RecordMapper {

  private RecordMapper() {
  }

  public static RecordDto entityToDto(Record recordEntity) {
    return new RecordDto(
        recordEntity.getId(),
        recordEntity.getRecordData()
    );
  }

}
