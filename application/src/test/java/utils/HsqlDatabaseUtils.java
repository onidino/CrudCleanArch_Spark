package utils;

import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class HsqlDatabaseUtils {

  protected AutoCloseable closeable;

  @AfterEach
  public void closeMocks() throws Exception {
    truncateTable();
    closeable.close();
  }

  private Sql2o sql2o;

  private static final String CREATE_RECORD_TABLE = "CREATE TABLE record (\n"
      + "  id int NOT NULL IDENTITY,\n"
      + "  record_data varchar(50) NOT NULL,\n"
      + "  created_date datetime NULL,\n"
      + "  updated_date datetime NULL,\n"
      + "  PRIMARY KEY (id)\n"
      + ")";

  public void createLocalTable() {
    this.sql2o = new Sql2o("jdbc:hsqldb:mem:testDB", "sa", "");
    Connection conn = sql2o.beginTransaction();
    conn.createQuery(CREATE_RECORD_TABLE).executeUpdate();
  }

  public DataSource getLocalHsqlDataSource() {
    return this.sql2o.getDataSource();
  }

  public void truncateTable() {
    Connection conn = sql2o.beginTransaction();
    conn.createQuery("TRUNCATE TABLE record").executeUpdate();
    conn.close();
  }
}
