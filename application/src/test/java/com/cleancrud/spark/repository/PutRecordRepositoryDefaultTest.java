package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import utils.HsqldbTestUtils;

class PutRecordRepositoryDefaultTest extends HsqldbTestUtils {

  @InjectMocks
  private PutRecordRepositoryDefault putRecordRepositoryDefault;

  @BeforeEach
  public void initMocks() {
    initLocalDB();
    putRecordRepositoryDefault = new PutRecordRepositoryDefault(getLocalHsqlDataSource());
    super.closeable = MockitoAnnotations.openMocks(this);
  }


  @Test
  void whenPutRecordRepositoryThenOk() {
    insertRecord("test");
    Record recordModified = new Record(0L, "test_updated");

    Optional<Record> result = putRecordRepositoryDefault.execute(recordModified);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(recordModified.getId(), result.get().getId());
    Assertions.assertEquals(recordModified.getRecordData(), result.get().getRecordData());
  }

  @Test
  void whenPutRecordRepositoryAndNotFoundThenOk() {
    insertRecord("test");
    Record recordModified = new Record(1L, "test_updated");

    Optional<Record> result = putRecordRepositoryDefault.execute(recordModified);

    Assertions.assertTrue(result.isEmpty());
  }

}