package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import repository.PostRecordRepository;

@Singleton
public class PostRecordRepositoryDefault implements PostRecordRepository {

  private static final String INSERT_QUERY = ""
      + "INSERT INTO record (record_data) "
      + "values (:record_data)";

  private final Sql2o sql2o;

  @Inject
  public PostRecordRepositoryDefault(final DataSource dataSource) {
    this.sql2o = new Sql2o(dataSource);
  }

  @Override
  public Optional<Long> execute(Record recordToSave) {
    Optional<Long> recordId;

    try (Connection conn = sql2o.beginTransaction()) {
      try (Query queryToExecute = conn.createQuery(INSERT_QUERY)) {
        recordId = Optional.of(queryToExecute
            .addParameter("record_data", recordToSave.getRecordData())
            .executeUpdate()
            .getKey(Long.class));
        conn.commit();

        return recordId;
      }
    }
  }

}
