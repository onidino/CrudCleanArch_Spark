package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import repository.GetRecordRepository;

public class GetRecordRepositoryDefault implements GetRecordRepository {

  @Override
  public Optional<Record> execute(Long id) {
    return Optional.empty();
  }
}
