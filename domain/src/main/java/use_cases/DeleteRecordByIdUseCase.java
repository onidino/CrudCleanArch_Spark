package use_cases;

import exception.UseCaseException;

/**
 * Delete record with id.
 */
@FunctionalInterface
public interface DeleteRecordByIdUseCase {

  void execute(Long id) throws UseCaseException;

}
