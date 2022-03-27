package com.cleancrud.spark.repository;

import java.util.Optional;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import repository.DeleteRecordRepository;

public class DeleteRecordRepositoryDefault implements DeleteRecordRepository {

  private static final String DELETE_QUERY = ""
      + "DELETE "
      + "FROM record "
      + "WHERE id = :id";

  private final Sql2o sql2o;

  @Inject
  public DeleteRecordRepositoryDefault(final DataSource dataSource) {
    this.sql2o = new Sql2o(dataSource);
  }

  @Override
  public Optional<Boolean> execute(Long id) {
    try (Connection conn = sql2o.beginTransaction()) {
      try (Query queryToExecute = conn.createQuery(DELETE_QUERY)) {
        int deleted = queryToExecute
            .addParameter("id", id)
            .executeUpdate()
            .getResult();
        conn.commit();

        return Optional.of(deleted > 0);
      }
    }
  }
}
