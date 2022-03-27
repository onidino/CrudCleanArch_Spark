package com.cleancrud.spark.module;

import com.cleancrud.spark.repository.DeleteRecordRepositoryDefault;
import com.cleancrud.spark.repository.GetRecordRepositoryDefault;
import com.cleancrud.spark.repository.PostRecordRepositoryDefault;
import com.cleancrud.spark.repository.PutRecordRepositoryDefault;
import com.google.inject.AbstractModule;
import repository.DeleteRecordRepository;
import repository.GetRecordRepository;
import repository.PostRecordRepository;
import repository.PutRecordRepository;

public class RepositoryModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(GetRecordRepository.class).to(GetRecordRepositoryDefault.class);
    bind(DeleteRecordRepository.class).to(DeleteRecordRepositoryDefault.class);
    bind(PostRecordRepository.class).to(PostRecordRepositoryDefault.class);
    bind(PutRecordRepository.class).to(PutRecordRepositoryDefault.class);
  }

}
