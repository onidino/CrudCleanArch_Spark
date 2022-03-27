package use_cases.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import entity.Record;
import exception.UseCaseException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.DeleteRecordRepository;
import use_cases.GetRecordByIdUseCase;
import utils.BaseUnitTest;

class DeleteRecordByIdUseCaseDefaultTest extends BaseUnitTest {

  private static final Long ID = 1L;

  @Mock
  private GetRecordByIdUseCase getRecordByIdUseCase;
  @Mock
  private DeleteRecordRepository deleteRecordRepository;

  @InjectMocks
  private DeleteRecordByIdUseCaseDefault deleteRecordByIdUseCaseDefault;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenDeleteRecordThenOk() throws UseCaseException {
    // given
    Record recordFound = Record.builder()
        .id(1L)
        .recordData("create_test")
        .build();

    // when
    ArgumentCaptor<Long> argumentCaptorId = ArgumentCaptor.forClass(Long.class);

    when(getRecordByIdUseCase.execute(anyLong())).thenReturn(recordFound);
    when(deleteRecordRepository.execute(argumentCaptorId.capture())).thenReturn(Optional.of(true));

    // then
    Assertions.assertDoesNotThrow(() -> deleteRecordByIdUseCaseDefault.execute(ID));

    // asserts
    Assertions.assertEquals(ID, argumentCaptorId.getValue());

    Mockito.verify(getRecordByIdUseCase, times(1)).execute(anyLong());
    Mockito.verify(deleteRecordRepository, times(1)).execute(anyLong());
  }

  @Test
  void whenCreateNewRecordFailsThenThrowsException() throws UseCaseException {
    // when
    Record recordFound = Record.builder()
        .id(1L)
        .recordData("create_test")
        .build();

    ArgumentCaptor<Long> argumentCaptorId = ArgumentCaptor.forClass(Long.class);

    when(getRecordByIdUseCase.execute(anyLong())).thenReturn(recordFound);
    when(deleteRecordRepository.execute(argumentCaptorId.capture())).thenReturn(Optional.empty());

    // then
    UseCaseException ex = Assertions.assertThrows(UseCaseException.class,
        () -> deleteRecordByIdUseCaseDefault.execute(ID));

    // asserts
    Assertions.assertNotNull(ex);
    Assertions.assertEquals("DELETE: Could not delete record with id [1]", ex.getMessage());

    Assertions.assertEquals(ID, argumentCaptorId.getValue());

    Mockito.verify(getRecordByIdUseCase, times(1)).execute(any());
    Mockito.verify(deleteRecordRepository, times(1)).execute(any());
  }
}