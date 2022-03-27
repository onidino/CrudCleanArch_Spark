package com.cleancrud.spark.module;

import com.cleancrud.spark.config.MySqlDatabase;
import com.google.inject.AbstractModule;
import javax.sql.DataSource;

public class DatabaseModule extends AbstractModule {

  @Override
  protected void configure() {
    final DataSource dataSource = new MySqlDatabase().configMySQLDatasource();

    bind(DataSource.class).toInstance(dataSource);

    super.configure();
  }
}
