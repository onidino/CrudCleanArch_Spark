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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import repository.PutRecordRepository;
import use_cases.GetRecordByIdUseCase;
import utils.BaseUnitTest;

class UpdateRecordUseCaseDefaultTest extends BaseUnitTest {

  private static final Long ID = 1L;
  private static final String DATA = "update_test";

  @Mock
  private GetRecordByIdUseCase getRecordByIdUseCase;

  @Mock
  private PutRecordRepository putRecordRepository;

  @InjectMocks
  private UpdateRecordUseCaseDefault updateRecordUseCaseDefault;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenUpdateNewRecordThenOk() throws UseCaseException {
    // given
    Record recordFound = Record.builder()
        .id(1L)
        .data("create_test")
        .build();

    Record recordUpdated = Record.builder()
        .id(1L)
        .data("create_updated")
        .build();

    // when
    when(getRecordByIdUseCase.execute(anyLong())).thenReturn(recordFound);
    when(putRecordRepository.execute(any())).thenReturn(Optional.of(recordUpdated));

    // then
    Record result = updateRecordUseCaseDefault.execute(ID, DATA);

    // asserts
    Assertions.assertNotNull(result);
    Assertions.assertEquals(recordUpdated.getId(), result.getId());
    Assertions.assertEquals(recordUpdated.getData(), result.getData());

    Mockito.verify(getRecordByIdUseCase, times(1)).execute(any());
    Mockito.verify(putRecordRepository, times(1)).execute(any());
  }

  @Test
  void whenUpdateNewRecordFailsThenThrowsException() throws UseCaseException {
    // given
    Record recordFound = Record.builder()
        .id(1L)
        .data("create_test")
        .build();

    // when
    when(getRecordByIdUseCase.execute(anyLong())).thenReturn(recordFound);
    when(putRecordRepository.execute(any())).thenReturn(Optional.empty());

    // then
    UseCaseException ex = Assertions.assertThrows(UseCaseException.class,
        () -> updateRecordUseCaseDefault.execute(ID, DATA));

    // asserts
    Assertions.assertNotNull(ex);
    Assertions.assertEquals("UPDATE: Could not update record with id [1]", ex.getMessage());

    Mockito.verify(getRecordByIdUseCase, times(1)).execute(any());
    Mockito.verify(putRecordRepository, times(1)).execute(any());
  }

}
