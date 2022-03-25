package com.cleancrud.spark.repository;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import utils.BaseUnitTest;

class DeleteRecordRepositoryDefaultTest extends BaseUnitTest {

  @InjectMocks
  private DeleteRecordRepositoryDefault deleteRecordRepositoryDefault;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenDeleteRecordRepositoryThenOk() {
    Optional<Boolean> result = deleteRecordRepositoryDefault.execute(1L);

    Assertions.assertEquals(Optional.empty(), result);
  }
}