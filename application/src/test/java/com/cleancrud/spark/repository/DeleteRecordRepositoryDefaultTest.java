package com.cleancrud.spark.repository;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import utils.HsqldbTestUtils;

class DeleteRecordRepositoryDefaultTest extends HsqldbTestUtils {

  @InjectMocks
  private DeleteRecordRepositoryDefault deleteRecordRepositoryDefault;

  @BeforeEach
  public void initMocks() {
    initLocalDB();
    deleteRecordRepositoryDefault = new DeleteRecordRepositoryDefault(getLocalHsqlDataSource());
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenDeleteRecordRepositoryThenOk() {
    insertRecord("test");

    Optional<Boolean> result = deleteRecordRepositoryDefault.execute(0L);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertTrue(result.get());
  }

  @Test
  void whenDeleteRecordRepositoryAndNotFoundThenOk() {
    insertRecord("test");

    Optional<Boolean> result = deleteRecordRepositoryDefault.execute(1L);

    Assertions.assertTrue(result.isPresent());
    Assertions.assertFalse(result.get());
  }
}