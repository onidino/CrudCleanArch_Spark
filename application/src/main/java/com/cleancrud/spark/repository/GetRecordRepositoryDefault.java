package com.cleancrud.spark.repository;

import entity.Record;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import repository.GetRecordRepository;

public class GetRecordRepositoryDefault implements GetRecordRepository {

  private static final String SELECT_QUERY = ""
      + "SELECT id, record_data "
      + "FROM record "
      + "WHERE id = :id";

  private final Sql2o sql2o;

  @Inject
  public GetRecordRepositoryDefault(final DataSource dataSource) {
    this.sql2o = new Sql2o(dataSource);
  }

  @Override
  public Optional<Record> execute(Long id) {
    try (Connection conn = sql2o.beginTransaction()) {
      try (Query queryToExecute = conn.createQuery(SELECT_QUERY)) {
        List<Record> result = queryToExecute
            .addParameter("id", id)
            .addColumnMapping("record_data", "recordData")
            .executeAndFetch(Record.class);

        return result.stream().findFirst();
      }
    }
  }

}
