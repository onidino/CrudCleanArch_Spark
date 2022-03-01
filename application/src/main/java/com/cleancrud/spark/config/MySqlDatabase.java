package com.cleancrud.spark.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Sql2o;

public class MySqlDatabase {

  private static final Logger LOGGER = LoggerFactory.getLogger(MySqlDatabase.class);

  private static final String DATABASE_PROPERTIES = "database";
  private static final String DATABASE_URL = "db.mysql.conn.url";
  private static final String DATABASE_HOST = "db.mysql.conn.host";
  private static final String DATABASE_PORT = "db.mysql.conn.port";
  private static final String DATABASE_SCHEMA = "db.mysql.conn.schema";
  private static final String DATABASE_USERNAME = "db.mysql.conn.username";
  private static final String DATABASE_PASSWORD = "db.mysql.conn.password";

  private String url;
  private String host;
  private int port;
  private String schema;
  private String username;
  private String password;

  private Sql2o sql2o;

  public DataSource configMySQLDatasource() {
    initWithDefaults();

    String urlConfig = String.format("%s://%s:%s/%s", url, host, port, schema);

    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setDatabaseName(schema);
    dataSource.setURL(urlConfig);
    dataSource.setServerName(host);
    dataSource.setPort(port);
    dataSource.setUser(username);
    dataSource.setPassword(password);
    this.sql2o = new Sql2o(dataSource);

    return dataSource;
  }

  private void initWithDefaults() {
    url = getDatabasePropertyValue(DATABASE_URL, "");
    host = getDatabasePropertyValue(DATABASE_HOST, "0.0.0.0");
    port = Integer.parseInt(getDatabasePropertyValue(DATABASE_PORT, "0"));
    schema = getDatabasePropertyValue(DATABASE_SCHEMA, "");
    username = getDatabasePropertyValue(DATABASE_USERNAME, "");
    password = getDatabasePropertyValue(DATABASE_PASSWORD, "");
  }

  private String getDatabasePropertyValue(String key, String defaultValue) {
    try {
      ResourceBundle serverProperties = ResourceBundle.getBundle(DATABASE_PROPERTIES);
      return serverProperties.getString(key);
    } catch (MissingResourceException ex) {
      return defaultValue;
    }
  }

  public DataSource getMySqlDatasource() {
    return this.sql2o.getDataSource();
  }
}
