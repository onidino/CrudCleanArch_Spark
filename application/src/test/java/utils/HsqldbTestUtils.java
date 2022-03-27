package utils;

import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class HsqldbTestUtils {

  private static final String CREATE_RECORD_TABLE = "CREATE TABLE record (\n"
      + "  id int NOT NULL IDENTITY,\n"
      + "  record_data varchar(50) NOT NULL,\n"
      + "  created_date datetime NULL,\n"
      + "  updated_date datetime NULL,\n"
      + "  PRIMARY KEY (id)\n"
      + ")";

  private static final String INSERT_RECORD_TABLE = ""
      + "INSERT INTO record (record_data) "
      + "VALUES :record_data";

  protected AutoCloseable closeable;

  private Sql2o sql2o;

  @AfterEach
  public void closeMocks() throws Exception {
    closeable.close();
    dropLocalTable();
  }

  public void initLocalDB() {
    createLocalTable();
  }

  public void createLocalTable() {
    this.sql2o = new Sql2o("jdbc:hsqldb:mem:testDB", "sa", "");
    Connection conn = sql2o.beginTransaction();
    conn.createQuery(CREATE_RECORD_TABLE).executeUpdate();
  }

  public DataSource getLocalHsqlDataSource() {
    return this.sql2o.getDataSource();
  }

  public void dropLocalTable() {
    Connection conn = sql2o.beginTransaction();
    conn.createQuery("DROP TABLE record").executeUpdate();
    conn.commit();
    conn.close();
  }

  public void insertRecord(String data) {
    Connection conn = sql2o.beginTransaction();
    conn.createQuery(INSERT_RECORD_TABLE)
        .addParameter("record_data", data)
        .executeUpdate();
    conn.commit();
    conn.close();
  }
}
