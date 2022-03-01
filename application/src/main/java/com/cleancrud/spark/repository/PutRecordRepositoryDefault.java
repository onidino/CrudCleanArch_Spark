package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import repository.PutRecordRepository;

public class PutRecordRepositoryDefault implements PutRecordRepository {

  @Override
  public Optional<Record> execute(Record recordModified) {
    return Optional.empty();
  }
}
