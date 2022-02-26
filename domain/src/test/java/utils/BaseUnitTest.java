package utils;

import org.junit.jupiter.api.AfterEach;

public class BaseUnitTest {

  protected AutoCloseable closeable;

  @AfterEach
  public void closeMocks() throws Exception {
    closeable.close();
  }
}
