package com.cleancrud.spark.module;

import com.cleancrud.spark.entrypoint.DeleteEntryPoint;
import com.cleancrud.spark.entrypoint.GetEntryPoint;
import com.cleancrud.spark.entrypoint.PostEntryPoint;
import com.cleancrud.spark.entrypoint.PutEntryPoint;
import com.google.inject.AbstractModule;

public class EntryPointModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(GetEntryPoint.class);
    bind(PostEntryPoint.class);
    bind(PutEntryPoint.class);
    bind(DeleteEntryPoint.class);
  }
}
