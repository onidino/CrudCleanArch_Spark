package use_cases.impl;

import static org.mockito.ArgumentMatchers.any;
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
import repository.PostRecordRepository;
import utils.BaseUnitTest;

class CreateRecordUseCaseDefaultTest extends BaseUnitTest {

  @Mock
  private PostRecordRepository postRecordRepository;

  @InjectMocks
  private CreateRecordUseCaseDefault createRecordUseCaseDefault;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
  }

  @Test
  void whenCreateNewRecordThenOk() throws UseCaseException {
    // given
    Record recordSaved = Record.builder()
        .id(1L)
        .recordData("create_test")
        .build();

    // when
    when(postRecordRepository.execute(any())).thenReturn(Optional.of(recordSaved.getId()));

    // then
    Record result = createRecordUseCaseDefault.execute("create_test");

    // asserts
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getId(), recordSaved.getId());
    Assertions.assertEquals(result.getRecordData(), recordSaved.getRecordData());
  }

  @Test
  void whenCreateNewRecordFailsThenThrowsException() {
    // when
    when(postRecordRepository.execute(any())).thenReturn(Optional.empty());

    // then
    UseCaseException ex = Assertions.assertThrows(UseCaseException.class,
        () -> createRecordUseCaseDefault.execute("create_test"));

    // asserts
    Assertions.assertNotNull(ex);
    Assertions.assertEquals("CREATE: Could not create record", ex.getMessage());

    Mockito.verify(postRecordRepository, times(1)).execute(any());
  }

}
