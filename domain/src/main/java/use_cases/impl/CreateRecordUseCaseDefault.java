package use_cases.impl;

import entity.Record;
import exception.UseCaseException;
import javax.inject.Inject;
import javax.inject.Singleton;
import repository.PostRecordRepository;
import use_cases.CreateRecordUseCase;

@Singleton
public class CreateRecordUseCaseDefault implements CreateRecordUseCase {

  private final PostRecordRepository postRecordRepository;

  @Inject
  public CreateRecordUseCaseDefault(final PostRecordRepository postRecordRepository) {
    this.postRecordRepository = postRecordRepository;
  }

  @Override
  public Record execute(String data) throws UseCaseException {
    Record newRecord = Record.builder()
        .recordData(data)
        .build();

    Long id = postRecordRepository.execute(newRecord)
        .orElseThrow(() -> new UseCaseException("CREATE: Could not create record"));

    newRecord.setId(id);

    return newRecord;
  }
}
