package repository;

import entity.Record;
import java.util.Optional;

/**
 * Interface to get record by id from the repository.
 */
@FunctionalInterface
public interface GetRecordRepository {

  Optional<Record> execute(Long id);

}
