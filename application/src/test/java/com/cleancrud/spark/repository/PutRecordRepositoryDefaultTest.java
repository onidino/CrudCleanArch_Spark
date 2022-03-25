package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import utils.BaseUnitTest;

class PutRecordRepositoryDefaultTest extends BaseUnitTest {

  @InjectMocks
  private PutRecordRepositoryDefault putRecordRepositoryDefault;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenPutRecordRepositoryThenOk() {
    Record recordMock = Record.builder().build();

    Optional<Record> result = putRecordRepositoryDefault.execute(recordMock);

    Assertions.assertEquals(Optional.empty(), result);
  }

}