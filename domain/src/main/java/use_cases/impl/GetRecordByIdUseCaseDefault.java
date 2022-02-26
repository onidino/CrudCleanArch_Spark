package use_cases.impl;

import entity.Record;
import exception.UseCaseException;
import javax.inject.Inject;
import javax.inject.Singleton;
import repository.GetRecordRepository;
import use_cases.GetRecordByIdUseCase;

@Singleton
public class GetRecordByIdUseCaseDefault implements GetRecordByIdUseCase {

  private final GetRecordRepository getRecordRepository;

  @Inject
  public GetRecordByIdUseCaseDefault(
      final GetRecordRepository getRecordRepository) {
    this.getRecordRepository = getRecordRepository;
  }

  @Override
  public Record execute(Long id) throws UseCaseException {
    return getRecordRepository.execute(id)
        .orElseThrow(() ->
            new UseCaseException(String.format("GET: Record not found for id [%s]", id)));
  }
}
