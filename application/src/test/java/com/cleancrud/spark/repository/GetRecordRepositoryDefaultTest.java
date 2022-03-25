package com.cleancrud.spark.repository;

import entity.Record;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import utils.BaseUnitTest;

class GetRecordRepositoryDefaultTest extends BaseUnitTest {

  @InjectMocks
  private GetRecordRepositoryDefault getRecordRepositoryDefault;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenGetRecordRepositoryThenOk() {
    Optional<Record> result = getRecordRepositoryDefault.execute(1L);

    Assertions.assertEquals(Optional.empty(), result);
  }

}