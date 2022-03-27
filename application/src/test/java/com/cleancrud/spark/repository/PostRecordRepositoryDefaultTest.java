package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import utils.HsqldbTestUtils;

class PostRecordRepositoryDefaultTest extends HsqldbTestUtils {

  @InjectMocks
  private PostRecordRepositoryDefault postRecordRepositoryDefault;

  @BeforeEach
  public void initMocks() {
    initLocalDB();
    postRecordRepositoryDefault = new PostRecordRepositoryDefault(getLocalHsqlDataSource());
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenPutRecordRepositoryThenOk() {
    Record recordMock = new Record(null, "test");

    Optional<Long> result = postRecordRepositoryDefault.execute(recordMock);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(0L, result.get());
  }

}