package com.cleancrud.spark.module;

import com.cleancrud.spark.entrypoint.GetEntryPoint;
import com.google.inject.AbstractModule;

public class EntryPointModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GetEntryPoint.class);
    }
}
