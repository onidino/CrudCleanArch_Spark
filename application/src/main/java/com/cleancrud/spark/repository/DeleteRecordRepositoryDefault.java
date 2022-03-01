package com.cleancrud.spark.repository;

import java.util.Optional;
import repository.DeleteRecordRepository;

public class DeleteRecordRepositoryDefault implements DeleteRecordRepository {

  @Override
  public Optional<Boolean> execute(Long id) {
    return Optional.empty();
  }
}
