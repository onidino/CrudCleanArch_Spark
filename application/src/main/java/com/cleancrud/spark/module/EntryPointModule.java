package com.cleancrud.spark.module;

import com.cleancrud.spark.entrypoint.impl.DeleteEntryPoint;
import com.cleancrud.spark.entrypoint.impl.GetEntryPoint;
import com.cleancrud.spark.entrypoint.impl.PostEntryPoint;
import com.cleancrud.spark.entrypoint.impl.PutEntryPoint;
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
