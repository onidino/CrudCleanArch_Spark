package use_cases;

import entity.Record;
import exception.UseCaseException;

/**
 * Get record by id.
 */
@FunctionalInterface
public interface GetRecordByIdUseCase {

  Record execute(Long id) throws UseCaseException;

}
