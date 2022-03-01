package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import repository.PostRecordRepository;

@Singleton
public class PostRecordRepositoryDefault implements PostRecordRepository {

  private static final String RECORD_DATA = "record_data";

  private static final String INSERT = ""
      + "INSERT INTO record (record_data) "
      + "values (:record_data)";

  private final Sql2o sql2o;

  @Inject
  public PostRecordRepositoryDefault(final DataSource dataSource) {
    this.sql2o = new Sql2o(dataSource);
  }

  @Override
  public Optional<Long> execute(Record recordToSave) {
    try (Connection conn = sql2o.beginTransaction()) {
      Optional<Long> recordId = Optional.of(
          conn.createQuery(INSERT)
              .addParameter(RECORD_DATA, recordToSave.getData())
              .executeUpdate()
              .getKey(Long.class));
      conn.commit();
      return recordId;
    }
  }
}
