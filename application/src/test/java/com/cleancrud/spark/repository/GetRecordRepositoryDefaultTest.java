package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import utils.HsqldbTestUtils;

class GetRecordRepositoryDefaultTest extends HsqldbTestUtils {

  @InjectMocks
  private GetRecordRepositoryDefault getRecordRepositoryDefault;

  @BeforeEach
  public void init() {
    initLocalDB();
    getRecordRepositoryDefault = new GetRecordRepositoryDefault(getLocalHsqlDataSource());
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenGetRecordRepositoryThenOk() {
    insertRecord("test");
    Record recordSaved = new Record(0L, "test");

    Optional<Record> result = getRecordRepositoryDefault.execute(0L);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(recordSaved, result.get());
    Assertions.assertEquals(recordSaved.getId(), result.get().getId());
    Assertions.assertEquals(recordSaved.getRecordData(), result.get().getRecordData());
  }

  @Test
  void whenGetRecordRepositoryAndNotFoundThenOk() {
    insertRecord("test");

    Optional<Record> result = getRecordRepositoryDefault.execute(1L);

    Assertions.assertTrue(result.isEmpty());
  }

}