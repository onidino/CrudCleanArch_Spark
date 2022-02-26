package use_cases.impl;

import entity.Record;
import exception.UseCaseException;
import javax.inject.Inject;
import javax.inject.Singleton;
import repository.DeleteRecordRepository;
import use_cases.DeleteRecordByIdUseCase;
import use_cases.GetRecordByIdUseCase;

@Singleton
public class DeleteRecordByIdUseCaseDefault implements DeleteRecordByIdUseCase {

  private final GetRecordByIdUseCase getRecordByIdUseCase;
  private final DeleteRecordRepository deleteRecordRepository;

  @Inject
  public DeleteRecordByIdUseCaseDefault(
      final GetRecordByIdUseCase getRecordByIdUseCase,
      final DeleteRecordRepository deleteRecordRepository) {
    this.getRecordByIdUseCase = getRecordByIdUseCase;
    this.deleteRecordRepository = deleteRecordRepository;
  }

  @Override
  public void execute(Long id) throws UseCaseException {
    Record recordFound = getRecordByIdUseCase.execute(id);

    Boolean recordDeleted = deleteRecordRepository.execute(recordFound.getId()).orElse(null);
    if (recordDeleted == null || !recordDeleted) {
      throw new UseCaseException(
          String.format("DELETE: Could not delete record with id [%s]", id));
    }
  }

}
