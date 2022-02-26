package repository;

import java.util.Optional;

/**
 * Interface to delete record by id from the repository.
 */
@FunctionalInterface
public interface DeleteRecordRepository {

  Optional<Boolean> execute(Long id);

}
