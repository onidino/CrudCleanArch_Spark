package use_cases;

import entity.Record;
import exception.UseCaseException;

/**
 * Create record with id and data.
 */
@FunctionalInterface
public interface CreateRecordUseCase {

  Record execute(String data) throws UseCaseException;

}
