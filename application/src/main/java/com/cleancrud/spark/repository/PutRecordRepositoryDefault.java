package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import repository.PutRecordRepository;

public class PutRecordRepositoryDefault implements PutRecordRepository {

  private static final String DELETE_QUERY = ""
      + "UPDATE record "
      + "SET record_data = :record_data "
      + "WHERE id = :id";

  private final Sql2o sql2o;

  @Inject
  public PutRecordRepositoryDefault(final DataSource dataSource) {
    this.sql2o = new Sql2o(dataSource);
  }

  @Override
  public Optional<Record> execute(Record recordModified) {
    try (Connection conn = sql2o.beginTransaction()) {
      try (Query queryToExecute = conn.createQuery(DELETE_QUERY)) {
        int updated = queryToExecute
            .addParameter("record_data", recordModified.getRecordData())
            .addParameter("id", recordModified.getId())
            .executeUpdate()
            .getResult();
        conn.commit();

        return updated > 0
            ? Optional.of(recordModified)
            : Optional.empty();
      }
    }
  }

}
