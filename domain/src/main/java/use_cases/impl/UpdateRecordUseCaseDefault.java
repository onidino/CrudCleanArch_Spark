package use_cases.impl;

import entity.Record;
import exception.UseCaseException;
import javax.inject.Inject;
import javax.inject.Singleton;
import repository.PutRecordRepository;
import use_cases.GetRecordByIdUseCase;
import use_cases.UpdateRecordUseCase;

@Singleton
public class UpdateRecordUseCaseDefault implements UpdateRecordUseCase {

  private final GetRecordByIdUseCase getRecordByIdUseCase;
  private final PutRecordRepository putRecordRepository;

  @Inject
  public UpdateRecordUseCaseDefault(
      final GetRecordByIdUseCase getRecordByIdUseCase,
      final PutRecordRepository putRecordRepository) {
    this.getRecordByIdUseCase = getRecordByIdUseCase;
    this.putRecordRepository = putRecordRepository;
  }

  @Override
  public Record execute(Long id, String data) throws UseCaseException {
    Record recordToUpdate = getRecordByIdUseCase.execute(id);
    recordToUpdate.setData(data);

    return putRecordRepository.execute(recordToUpdate)
        .orElseThrow(() -> new UseCaseException(
            String.format("UPDATE: Could not update record with id [%s]", id)));
  }

}
