package com.cleancrud.spark.module;

import com.google.inject.AbstractModule;
import use_cases.CreateRecordUseCase;
import use_cases.DeleteRecordByIdUseCase;
import use_cases.GetRecordByIdUseCase;
import use_cases.UpdateRecordByIdUseCase;
import use_cases.impl.CreateRecordUseCaseDefault;
import use_cases.impl.DeleteRecordByIdUseCaseDefault;
import use_cases.impl.GetRecordByIdUseCaseDefault;
import use_cases.impl.UpdateRecordByIdUseCaseDefault;

public class UseCaseModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(GetRecordByIdUseCase.class).to(GetRecordByIdUseCaseDefault.class);
    bind(CreateRecordUseCase.class).to(CreateRecordUseCaseDefault.class);
    bind(UpdateRecordByIdUseCase.class).to(UpdateRecordByIdUseCaseDefault.class);
    bind(DeleteRecordByIdUseCase.class).to(DeleteRecordByIdUseCaseDefault.class);
  }
}
