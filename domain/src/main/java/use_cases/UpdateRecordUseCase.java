package use_cases;

import entity.Record;
import exception.UseCaseException;

/**
 * Update record with id and data.
 */
@FunctionalInterface
public interface UpdateRecordUseCase {

  Record execute(Long id, String data) throws UseCaseException;

}
