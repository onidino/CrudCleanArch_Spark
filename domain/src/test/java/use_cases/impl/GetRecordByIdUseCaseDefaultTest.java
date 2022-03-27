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
import repository.GetRecordRepository;
import utils.BaseUnitTest;

class GetRecordByIdUseCaseDefaultTest extends BaseUnitTest {

  private static final Long ID = 1L;

  @Mock
  private GetRecordRepository getRecordRepository;

  @InjectMocks
  private GetRecordByIdUseCaseDefault getRecordByIdUseCaseDefault;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenGetRecordByIdThenOk() throws UseCaseException {
    // given
    Record recordFound = Record.builder()
        .id(1L)
        .recordData("create_test")
        .build();

    // when
    when(getRecordRepository.execute(anyLong())).thenReturn(Optional.of(recordFound));

    // then
    Record result = getRecordByIdUseCaseDefault.execute(ID);

    // asserts
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getId(), recordFound.getId());
    Assertions.assertEquals(result.getRecordData(), recordFound.getRecordData());
  }

  @Test
  void whenGetRecordByIdFailsThenThrowsException() {
    // when
    when(getRecordRepository.execute(anyLong())).thenReturn(Optional.empty());

    // then
    UseCaseException ex = Assertions.assertThrows(UseCaseException.class,
        () -> getRecordByIdUseCaseDefault.execute(ID));

    // asserts
    Assertions.assertNotNull(ex);
    Assertions.assertEquals("GET: Record not found for id [1]", ex.getMessage());

    Mockito.verify(getRecordRepository, times(1)).execute(any());
  }

}
